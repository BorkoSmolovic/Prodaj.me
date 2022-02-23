<template>
  <div class="container"> 
    <div class="row justify-content-center">
      <div class="mrgnbtm">
        <h2>Napravi novi nalog</h2>
        <form>
          <div class="row">
            <div class="form-group col-md-6">
              <label htmlFor="exampleInputEmail1">*Ime</label>
              <input
                type="text"
                class="form-control"
                v-model="firstName"
                name="firstname"
                id="firstname"
                aria-describedby=""
                placeholder="Ime"
              />
            </div>
            <div class="form-group col-md-6">
              <label htmlFor="exampleInputPassword1">*Prezime</label>
              <input
                type="text"
                class="form-control"
                v-model="lastName"
                name="lastname"
                id="lastname"
                placeholder="Prezime"
              />
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-12">
              <label htmlFor="exampleInputEmail1">*Email</label>
              <input
                type="text"
                class="form-control"
                v-model="email"
                name="email"
                id="email"
                aria-describedby="emailHelp"
                placeholder="Email"
              />
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-12">
              <label htmlFor="exampleInputEmail1">*Šifra</label>
              <input
                type="password"
                class="form-control"
                v-model="password"
                name="password"
                id="password"
                aria-describedby=""
                placeholder="Šifra"
              />
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-12">
              <label htmlFor="exampleInputEmail1">*Potvrdite šifru</label>
              <input
                type="password"
                class="form-control"
                v-model="confirmPassword"
                name="confirmPassword"
                id="confirmPassword"
                aria-describedby=""
                placeholder="Potvrdite šifru"
              />
            </div>
          </div>
          <button type="button" @click="createUser()" class="btn btn-danger">
            <div v-show="!loading">Registruj se</div>
            <b-spinner v-show="loading" label="Loading..."></b-spinner>
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { createUser } from "../services/UserService";
import Swal from "sweetalert2";

export default {
  name: "CreateUser",
  data() {
    return {
      loading: false,
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      confirmPassword: "",
    };
  },
  methods: {
    createUser() {
      this.loading = true;
      const data = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
      };
      createUser(data).then((response) => {
        this.loading = false;
        console.log(response);
        Swal.fire({
          icon: "success",
          title: "Uspješna registracija!",
          showConfirmButton: true,
          confirmButtonText: "Potvrdi",
          timer: 1500,
        });
      });
      this.clearForm();
    },
    clearForm() {
      this.firstName = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
      this.confirmPassword = "";
    },
  },
};
</script>