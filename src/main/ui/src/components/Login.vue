<template>
     <v-app>
    <v-dialog v-model="dialog" persistent max-width="600px" min-width="360px">
      <div>
        <v-tabs
          v-model="tab"
          show-arrows
          background-color="#42A5F5"
          icons-and-text
          dark
          grow
        >
          <v-tabs-slider color="purple darken-4"></v-tabs-slider>
          <v-tab v-for="i in tabs" :key="i.name">
            <v-icon large>{{ i.icon }}</v-icon>
            <div class="caption py-1">{{ i.name }}</div>
          </v-tab>
          <v-tab-item>
            <v-card class="px-4">
              <v-card-text>
                <v-form ref="loginForm" v-model="validLogin" lazy-validation>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        v-model="loginEmail"
                        :rules="loginEmailRules"
                        label="*E-mail"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="loginPassword"
                        :append-icon="show1 ? 'eye' : 'eye-off'"
                        :rules="[rules.required, rules.min]"
                        :type="show1 ? 'text' : 'password'"
                        name="input-10-1"
                        label="*Šifra"
                        hint="Najmanje 8 karaktera"
                        counter
                        @click:append="show1 = !show1"
                      ></v-text-field>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="6" xsm="12"> </v-col>
                    <v-spacer></v-spacer>
                    <v-col class="d-flex" cols="12" sm="3" xsm="12" align-end>
                      <v-btn
                        x-large
                        block
                        :disabled="!validLogin"
                        color="success"
                        @click="validateLogin"
                        :loading="loginLoading"
                      >
                        Login
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card class="px-4">
              <v-card-text>
                <v-form
                  ref="registerForm"
                  v-model="validRegistration"
                  lazy-validation
                >
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
                        :rules="[rules.required, rules.min]"
                        :type="show1 ? 'text' : 'password'"
                        name="input-10-1"
                        label="*Šifra"
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
                        :rules="[rules.required, passwordMatch]"
                        :type="show1 ? 'text' : 'password'"
                        name="input-10-1"
                        label="*Potvrdite šifru"
                        counter
                        @click:append="show1 = !show1"
                      ></v-text-field>
                    </v-col>
                    <v-spacer></v-spacer>
                    <v-col class="d-flex ml-auto" cols="12" sm="3" xsm="12">
                      <v-btn
                        x-large
                        block
                        :disabled="!validRegistration"
                        color="success"
                        @click="validateRegistration"
                        :loading="registrationLoading"
                        >Register</v-btn
                      >
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs>
      </div>
    </v-dialog>
    </v-app>
</template>

<script>
import Swal from "sweetalert2";
import axios from "axios";

export default {
  name: "LoginUser",
   data: () => ({
    dialog: true,
    tab: 0,
    tabs: [
      { name: "Login", icon: "mdi-account" },
      { name: "Registracija", icon: "mdi-account-outline" },
    ],
    registrationLoading: false,
    loginLoading: false,
    validLogin: true,
    validRegistration: true,
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    verify: "",
    loginPassword: "admin123",
    loginEmail: "admin@gmail.com",
    loginEmailRules: [
      (v) => !!v || "Obavezno polje",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],
    emailRules: [
      (v) => !!v || "Obavezno polje",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],

    show1: false,
    rules: {
      required: (value) => !!value || "Obavezno polje",
      min: (v) => (v && v.length >= 8) || "Minimum 8 karaktera",
    },
  }),
  computed: {
    passwordMatch() {
      return () =>
        this.password === this.verify || "Šifre se moraju podudarati";
    },
  },
  methods: {
    clearForm() {
      this.firstName = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
      this.confirmPassword = "";
    },
    validateLogin() {
      this.loginLoading = true;
      if (this.$refs.loginForm.validate()) {
        const data = {
          email: this.loginEmail,
          password: this.loginPassword,
        };
        axios.post("/api/user/login", data).then((response) => {
          console.log(response.data);
          if (response.data) {
            localStorage.setItem("user",JSON.stringify(response.data));
            Swal.fire({
              icon: "success",
              title: "Dobrodošao " + response.data.firstName,
              showConfirmButton: true,
              confirmButtonText: "Potvrdi",
              timer: 1500,
            });
            this.resetLoginForm();
            this.$router.push('/dashboard');
          } else {
            Swal.fire({
              icon: "error",
              title: "Greška",
              text: "Neispravan e-mail ili šifra",
              showConfirmButton: true,
              confirmButtonText: "Potvrdi",
              timer: 1500,
            });
          }
          this.loginLoading = false;
        });
      }else{
        this.loginLoading = false;
      }
    },
    validateRegistration() {
      this.registrationLoading = true;
      if (this.$refs.registerForm.validate()) {
        const data = {
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password,
          active: 1,
        };
        axios.post("/api/user/register", data).then((response) => {
          this.registrationLoading = false;
          console.log(response.data);
          if(response.data.id){
            Swal.fire({
            icon: "success",
            title: "Uspješna registracija!",
            showConfirmButton: true,
            confirmButtonText: "Potvrdi",
            timer: 1500,
          });  
          this.resetRegisterForm();
          }
          
        });
      } else {
        this.registrationLoading = false;
      }
    },
    resetRegisterForm() {
      this.$refs.registerForm.reset();
    },
    resetLoginForm() {
      this.$refs.loginForm.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
  }
};
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css?family=Exo:400,700');

*{
    margin: 0px;
    padding: 0px;
}

body{
    font-family: 'Exo', sans-serif;
}


.context {
    width: 100%;
    position: absolute;
    top:50vh;
    
}

.context h1{
    text-align: center;
    color: #fff;
    font-size: 50px;
}


.area{
    background: #4e54c8;  
    background: -webkit-linear-gradient(to left, #8f94fb, #4e54c8);  
    width: 100%;
    height:100vh;
    
   
}

.circles{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}

.circles li{
    position: absolute;
    display: block;
    list-style: none;
    width: 20px;
    height: 20px;
    background: rgba(255, 255, 255, 0.2);
    animation: animate 25s linear infinite;
    bottom: -150px;
    
}

.circles li:nth-child(1){
    left: 25%;
    width: 80px;
    height: 80px;
    animation-delay: 0s;
}


.circles li:nth-child(2){
    left: 10%;
    width: 20px;
    height: 20px;
    animation-delay: 2s;
    animation-duration: 12s;
}

.circles li:nth-child(3){
    left: 70%;
    width: 20px;
    height: 20px;
    animation-delay: 4s;
}

.circles li:nth-child(4){
    left: 40%;
    width: 60px;
    height: 60px;
    animation-delay: 0s;
    animation-duration: 18s;
}

.circles li:nth-child(5){
    left: 65%;
    width: 20px;
    height: 20px;
    animation-delay: 0s;
}

.circles li:nth-child(6){
    left: 75%;
    width: 110px;
    height: 110px;
    animation-delay: 3s;
}

.circles li:nth-child(7){
    left: 35%;
    width: 150px;
    height: 150px;
    animation-delay: 7s;
}

.circles li:nth-child(8){
    left: 50%;
    width: 25px;
    height: 25px;
    animation-delay: 15s;
    animation-duration: 45s;
}

.circles li:nth-child(9){
    left: 20%;
    width: 15px;
    height: 15px;
    animation-delay: 2s;
    animation-duration: 35s;
}

.circles li:nth-child(10){
    left: 85%;
    width: 150px;
    height: 150px;
    animation-delay: 0s;
    animation-duration: 11s;
}



@keyframes animate {

    0%{
        transform: translateY(0) rotate(0deg);
        opacity: 1;
        border-radius: 0;
    }

    100%{
        transform: translateY(-1000px) rotate(720deg);
        opacity: 0;
        border-radius: 50%;
    }

}
</style>