<template>
  <v-container fluid grid-list-sm>
    <v-toolbar color="White">
      <v-toolbar-title class="headline"
        >Admin panel za upravljanje korisnicima</v-toolbar-title
      >
      <v-spacer></v-spacer>
      <v-btn
        class="mx-5"
        :loading="tableDataLoading"
        :disabled="tableDataLoading"
        color="primary"
        @click="goToDashboard"
      >
        <v-icon>mdi-arrow-bottom-left</v-icon>
        Dashboard
      </v-btn>
      <v-btn
        :loading="tableDataLoading"
        :disabled="tableDataLoading"
        color="warning"
        @click="readUsers()"
      >
        <v-icon>mdi-refresh</v-icon>
      </v-btn>
    </v-toolbar>
    <v-spacer></v-spacer>
    <v-data-table
      :mobile-breakpoint="breakpoint"
      :headers="headers"
      :items="tableData"
      :items-per-page="itemsPerPage"
      :footer-props="{ itemsPerPageOptions: [10, 20, 30, 50] }"
      :dense="true"
      :loading-text="'Učitavanje'"
      class="elevation-1"
      sort-by="name"
      :loading="tableDataLoading"
    >
      <template v-slot:item.active="{ item }">
        <v-chip
          small
          :class="{
            'error inactive-row white--text': !item.active,
            'white black--text': item.active,
          }"
        >
          {{ item.active ? "Aktivan" : "Neaktivan" }}
        </v-chip>
      </template>
      <!-------------------------------------  DETAILS EDIT DELETE  ----------------------------------------------->
      <template v-slot:item.actions="{ item }">
        <v-layout align-center justify-center>
          <v-btn icon @click.stop="openEditDialog(item)">
            <v-icon>mdi-pencil-outline</v-icon>
          </v-btn>
          <v-btn
            icon
            :loading="deleteLoadingId == item.id"
            @click="deactivateAccount(item)"
          >
            <v-icon v-if="item.active">mdi-trash-can-outline</v-icon>
            <v-icon v-else>mdi-backup-restore</v-icon>
          </v-btn>
        </v-layout>
      </template>
      <!----------------------------------- ## FILTERS ## ------------------------------------>
      <template v-slot:body.prepend>
        <tr class="grey lighten-2">
          <th v-for="header in headers" :key="header.name" class="py-2">
            <v-select
              v-if="header.name == 'active'"
              class="justify-center"
              v-model="filters[header.value]"
              @change="search"
              :items="activeObjects"
              item-value="value"
              item-text="text"
              style="
                padding: 0;
                margin-top: 2em !important;
                margin-bottom: -1em !important;
                font-size: inherit;
                text-align: right !important;
              "
              label="Filter"
            >
            </v-select>
            <v-text-field
              v-else-if="header.name != 'actions'"
              v-model="filters[header.value]"
              @keyup.enter.native="search"
              label="Filter"
              :placeholder="header.text"
              filled
              hide-details
              style="
                padding: 0;
                font-size: inherit;
                text-align: right !important;
              "
            ></v-text-field>
            <v-layout v-else class="justify-center">
              <v-btn
                depressed
                small
                :disabled="tableDataLoading"
                color="success"
                @click="search"
              >
                <v-icon>mdi-search-web</v-icon>
              </v-btn>
              <v-btn
                depressed
                class="ml-1"
                small
                :disabled="tableDataLoading"
                color="error"
                @click="refresh(true)"
              >
                <v-icon>mdi-close-circle</v-icon>
              </v-btn>
            </v-layout>
          </th>
        </tr>
      </template>
      <!-------------------------------- ## NO DATA ## ----------------------------------->
      <template v-slot:no-data>
        <div class="text-xs-center">
          <v-alert color="error" icon="mdi-alert-circle-outline">
            {{ errorMessage }}
          </v-alert>
        </div>
      </template>
    </v-data-table>
    <!---------------------------------- Edit item Dialog  ----------------------------------------->
    <UserUpdate
      :dialog="editUserDialog"
      :user="user"
      @closeDialog="editUserDialog = false"
      @update="readUsers()"
    />
    <!---------------------------------- Snackbar  ----------------------------------------->
    <v-snackbar
      v-model="snackbar.show"
      :bottom="true"
      :color="snackbar.color"
      :timeout="snackbar.timeout"
    >
      {{ snackbar.text }}
      <v-btn dark text @click="snackbar.show = false">
        <v-icon>mdi-close</v-icon>
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2"
import UserUpdate from "@/components/UserUpdate.vue";
export default {
  name: "AdminPanel",
  components: {
    UserUpdate,
  },
  data() {
    return {
      user: null,
      breakpoint: 500,
      editUserDialog: false,
      tableDataLoading: false,
      tableData: [],
      filteredTableData: [],
      filters: {
        active: "",
      },
      itemsPerPage: 10,
      deleteLoadingId: -1,
      errorMessage: "Nema podataka",
      snackbar: {
        color: "success",
        show: false,
        text: "",
        timeout: 3000,
      },
    };
  },
  methods: {
    deactivateAccount(user) {
        this.deleteLoadingId = user.id;
      let title, text, act, confirm, message;
      if (user.active == 1) {
        title = "Da li ste sigurni da želite da deaktivirate ovaj nalog?";
        text = "Nalog se može ponovo aktivirati u svakom momentu!";
        act = 0;
        confirm = "Da, deaktiviraj nalog!";
        message = "Nalog uspješno deaktiviran!";
      } else {
        title = "Da li ste sigurni da želite da ponovo aktivirate ovaj nalog?";
        text = "Nalog se može deaktivirati u svakom momentu!";
        act = 1;
        confirm = "Da, aktiviraj nalog!";
        message = "Nalog uspješno aktiviran!";
      }
      Swal.fire({
        title: title,
        text: text,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: confirm,
        cancelButtonText: "Odustani",
      }).then((result) => {
        if (result.isConfirmed) {
          const data = {
            id: user.id,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            password: user.password,
            active: act,
          };
          axios
            .put("/api/user/edit/" + user.id, data)
            .then((response) => {
              this.readUsers();
              response;
              this.deleteLoadingId = -1;
              Swal.fire({
                icon: "success",
                title: message,
                showConfirmButton: true,
                confirmButtonText: "Potvrdi",
                timer: 1500,
              });
            })
            .catch((error) => {
                this.deleteLoadingId = -1;
              console.log("greska", error.data);
            });
        }
        this.deleteLoadingId = -1;
      });
    },
    refresh(isReset) {
      if (this.filteredTableData.length == 0) {
        this.filteredTableData = this.tableData;
      }
      Object.keys(this.filters).forEach((f) => {
        this.filters[f] = "";
      });
      if (isReset) {
        this.tableData = this.filteredTableData;
      } else {
        this.readusers();
      }
    },
    goToDashboard() {
      this.$router.push("/dashboard");
    },
    search() {
      if (this.filteredTableData.length == 0) {
        this.filteredTableData = this.tableData;
      }
      this.tableData = this.filteredTableData;
      Object.keys(this.filters).forEach((val) => {
        if (this.filters[val] == "") {
          return;
        }
        this.tableData = this.tableData.filter((item) => {
          if (item != undefined && item != null) {
            //kreiraj varijablu koja sadrzi item
            let value = item;
            //ukoliko val koji predstavlja kljuc iz fitlera sadrzi tacku,
            //to znaci da treba uci u objekat, pa se to mora raditi preko []
            if (val.indexOf(".") != -1) {
              //dobijamo svaki kljuc
              let keys = val.split(".");
              //prolazimo kroz svaki kljuc i ulazimo u value kroz loop i value dobija vrijednost od kljuca
              keys.forEach((part) => {
                if (value != null) {
                  value = value[part];
                }
              });
            } else {
              //ako nema tacke onda samo postaje kao sto pise
              value = item[val];
            }
            return (
              (value + "")
                .toLowerCase()
                .indexOf((this.filters[val] + "").toLowerCase()) > -1
            );
          }
        });
      });
      if (this.tableData.length == 0) {
        this.errorMessage = "Nema podataka";
      }
    },
    readUsers() {
      axios.get("/api/users").then((response) => {
        this.tableData = response.data;
        this.filteredTableData = response.data;
      });
    },
    openEditDialog(item) {
      this.user = item;
      this.editUserDialog = true;
    },
  },
  computed: {
    headers() {
      return [
        { text: "Id", name: "Id", align: "left", value: "id" },
        { text: "Ime", name: "firstName", align: "left", value: "firstName" },
        { text: "Prezime", name: "lastName", align: "left", value: "lastName" },
        { text: "Email", name: "email", align: "left", value: "email" },
        //{ text: "Šifra", name: "password", align: "left", value: "password" },
        {
          text: "Aktivnost",
          name: "active",
          align: "center",
          sortable: false,
          value: "active",
        },
        {
          text: "Opcije",
          name: "actions",
          align: "center",
          sortable: false,
          value: "actions",
        },
      ];
    },
    activeObjects() {
      return [
        { value: "", text: "Svi" },
        { value: "1", text: "Aktivni" },
        { value: "0", text: "Neaktivni" },
      ];
    },
  },
  created() {
    this.readUsers();
  },
};
</script>

<style scoped>
</style>
