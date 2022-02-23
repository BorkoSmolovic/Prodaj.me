<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar dark color="primary">
          <v-toolbar-title>Detalji oglasa</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-card class="d-flex align-center primary" flat tile>
              <v-btn
                v-if="
                  ad
                    ? parseInt(loggedId) == parseInt(ad.ownerId) ||
                      parseInt(loggedId) == 1
                    : false
                "
                dark
                @click="dialogEdit = true"
                class="warning"
              >
                Izmijeni oglas
              </v-btn>
            </v-card>
            <v-card class="d-flex align-center primary" flat tile>
              <v-btn
                v-if="
                  ad
                    ? parseInt(loggedId) == parseInt(ad.ownerId) ||
                      parseInt(loggedId) == 1
                    : false
                "
                dark
                @click="deleteAd"
                class="error mx-4"
              >
                Obriši oglas
              </v-btn>
            </v-card>
            <v-btn icon dark @click="closeDialog">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <EditAd
          :ad="ad"
          :dialog="dialogEdit"
          :cities="cities"
          :categories="categories"
          :types="types"
          @closeDialog="dialogEdit = false"
          @loading="loading = $event"
          @edit="onEdit($event)"
        />
        <v-list v-if="!loading" three-line subheader>
          <v-row>
            <v-col cols="6" class="mt-5">
              <v-subheader class="justify-center mt-3"
                ><h6><b>Slike</b></h6></v-subheader
              >
              <v-carousel v-model="model" class="mx-5">
                <v-carousel-item
                  v-for="(item, i) in items"
                  :key="i"
                  :src="item.src"
                  reverse-transition="fade-transition"
                  transition="fade-transition"
                ></v-carousel-item>
              </v-carousel>
            </v-col>
            <v-col cols="6" class="mt-5">
              <v-row>
                <v-col cols="6"> </v-col>
                <v-col cols="6"> </v-col>
              </v-row>
              <v-subheader class="justify-center mt-3"
                ><h6><b>Detalji</b></h6></v-subheader
              >
              <v-list three-line subheader>
                <v-row>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle>Naziv</v-list-item-subtitle>
                        <v-list-item-title>{{
                          ad ? ad.name : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                  <v-col cols="6"> </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle>Grad</v-list-item-subtitle>
                        <v-list-item-title>{{
                          ad ? cities[ad.cityId - 1].name : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle>Kategorija</v-list-item-subtitle>
                        <v-list-item-title>{{
                          ad ? categories[ad.categoryId - 1].name : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle>Tip</v-list-item-subtitle>
                        <v-list-item-title>{{
                          ad ? types[ad.typeId - 1].name : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle>Cijena</v-list-item-subtitle>
                        <v-list-item-title>{{
                          ad ? ad.price + "€" : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle
                          >Ime prodavca</v-list-item-subtitle
                        >
                        <v-list-item-title>{{
                          ad ? ad.user.firstName + " " + ad.user.lastName : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                  <v-col cols="6">
                    <v-list-item>
                      <v-list-item-action> </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-subtitle
                          >Kontakt podaci</v-list-item-subtitle
                        >
                        <v-list-item-title>{{
                          ad ? ad.user.email : ""
                        }}</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-col>
                </v-row>
                <v-list-item>
                  <v-list-item-action> </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-subtitle>Opis</v-list-item-subtitle>
                    <v-list-item-title>{{
                      ad ? ad.description : ""
                    }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-col>
          </v-row>
        </v-list>
        <v-divider></v-divider>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
import EditAd from "@/components/EditAd.vue";
export default {
  props: {
    dialog: null,
    ad: null,
    categories: null,
    cities: null,
    types: null,
  },
  name: "DetailsAd",
  components: {
    EditAd,
  },
  data() {
    return {
      loading: false,
      dialogEdit: false,
      loggedId: -1,
      model: 0,
      items: [],
    };
  },
  methods: {
    onEdit(ad) {
      this.$emit("edit", ad);
    },
    deleteAd() {
      Swal.fire({
        title: "Da li ste sigurni da želite da izbrišete oglas?",
        text: "Izbrisani oglas se ne može vratiti!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da, obriši oglas!",
        cancelButtonText: "Odustani",
      }).then((result) => {
        if (result.isConfirmed) {
          axios
            .delete("/api/ad/delete/" + this.ad.id)
            .then((response) => {
              this.$emit("delete");
              response;
              Swal.fire({
                icon: "success",
                title: "Oglas uspješno obrisan!",
                showConfirmButton: true,
                confirmButtonText: "Potvrdi",
                timer: 1500,
              });
            })
            .catch((error) => {
              console.log("greska", error.data);
            });
          this.closeDialog();
        }
      });
    },
    closeDialog() {
      this.$emit("closeDialog");
    },
  },
  watch: {
    dialog: function(val){
      console.log("Desilo see")
      if(val){
        this.items = [];
        this.ad.images.forEach(element => {
          this.items.push({src: element.image})
        });
        if(this.ad.images.length == 0){
          this.items.push({src: "nema.jpeg"})
        }
      }
    }
  },
  created: function () {
    let user = JSON.parse(localStorage.getItem("user"));
    if (user != null) {
      this.loggedId = user.id;
    }
  },
};
</script>