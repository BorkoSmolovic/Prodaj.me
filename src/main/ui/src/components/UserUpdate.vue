


<template>
  <v-app>
    <v-row justify="center" data-app>
      <v-dialog v-if="dialog" v-model="dialog" persistent max-width="650px">
        <v-card>
          <v-card-title>
            <span class="headline">Izmijeni korisničke podatke</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-form ref="updateUserForm" v-model="valid" lazy-validation>
                <v-row>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="firstName"
                      :rules="[rules.required]"
                      label="*Ime"
                      maxlength="20"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      v-model="lastName"
                      :rules="[rules.required]"
                      label="*Prezime"
                      maxlength="20"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      v-model="email"
                      :rules="emailRules"
                      label="*E-mail"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      v-model="password"
                      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      :rules="[rules.min]"
                      :type="show1 ? 'text' : 'password'"
                      name="input-10-1"
                      label="*Nova šifra"
                      hint="At least 8 characters"
                      counter
                      @click:append="show1 = !show1"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      block
                      v-model="verify"
                      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      :rules="[passwordMatch]"
                      :type="show1 ? 'text' : 'password'"
                      name="input-10-1"
                      label="*Potvrdite novu šifru"
                      counter
                      @click:append="show1 = !show1"
                    ></v-text-field>
                  </v-col>
                  <v-spacer></v-spacer>
                  <v-col class="d-flex ml-auto" cols="12" sm="3" xsm="12">
                  </v-col>
                </v-row>
              </v-form>
            </v-container>
            <small>*označava obavezno polje</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="closeDialog">
              Zatvori
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="updateUser"
              :loading="registrationLoading"
            >
              Izmijeni</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-app>
</template>

<script>
import Swal from "sweetalert2";
import axios from "axios";

export default {
  name: "UpdateUser",
  props: {
    user:null,
    dialog: null,
  },
  data: () => ({
    loading: false,
    id: "",
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirmPassword: "",
    show1: false,
    verify: "",
    registrationLoading: false,
    valid: true,
    emailRules: [
      (v) => !!v || "Obavezno polje",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],
    rules: {
      required: (value) => !!value || "Obavezno polje",
      min: (v) => (!v || v.length <1  || v.length >= 8) || "Minimum 8 karaktera",
    },
  }),
  computed: {
    passwordMatch() {
      return () =>
          !this.password || this.password.length>=1 || this.password === this.verify || "Šifre se moraju podudarati"; 
    },
  },
  methods: {
    updateUser() {
      this.loading = true;
      let user = this.user;
      if (this.$refs.updateUserForm.validate()) {
        const data = {
          id: this.id,
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password,
          active: user.active,
        };
        if(!data.password || data.password.length < 8){
         data.password = user.password;
        }
        axios.put("/api/user/edit/" + this.id, data).then((response) => {
          this.loading = false;
          if (response.data.id) {
            localStorage.setItem("user",JSON.stringify(response.data));
            Swal.fire({
              icon: "success",
              title: "Uspješne izmjene!",
              showConfirmButton: true,
              confirmButtonText: "Potvrdi",
              timer: 1500,
            });
            this.$emit("update");
            this.closeDialog();
            this.clearForm();
            this.$refs.updateUserForm.reset();
          }
        });
      } else {
        this.loading = false;
      }
    },
    clearForm() {
      this.firstName = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
      this.confirmPassword = "";
    },
    closeDialog() {
      this.$emit("closeDialog");
      this.clearForm();
      this.$refs.updateUserForm.reset();
    },
  },
  watch: {
    dialog: function (val) {
      val;
      let user = this.user;
      console.log("user", user);
      this.id = user.id;
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.email = user.email;
    },
  },
};
</script>