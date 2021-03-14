<template>
  <v-row justify="center" data-app>
    <v-dialog v-model="dialog" persistent max-width="650px">
      <v-card>
        <v-card-title>
          <span class="headline">Dodaj oglas</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="addForm" v-model="validAdd" lazy-validation>
              <v-row>
                <v-col cols="12" sm="12" md="6">
                  <v-text-field
                    label="Naziv*"
                    v-model="name"
                    required
                    :rules="rules.required"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="12" md="6">
                  <v-text-field
                    v-model="price"
                    label="Cijena*"
                    hint="Cijena izražena u eurima"
                    append-icon="mdi-currency-eur"
                    :rules="rules.price"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="12" md="4">
                  <v-select
                    :items="categories"
                    v-model="categoryId"
                    label="Kategorija*"
                    item-text="name"
                    item-value="id"
                    single-line
                    solo
                    :rules="rules.required"
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="12" md="4">
                  <v-select
                    :items="types"
                    v-model="typeId"
                    label="Tip oglasa*"
                    item-text="name"
                    item-value="id"
                    single-line
                    solo
                    :rules="rules.required"
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="12" md="4">
                  <v-select
                    :items="cities"
                    v-model="cityId"
                    label="Grad*"
                    item-text="name"
                    item-value="id"
                    single-line
                    solo
                    :rules="rules.required"
                  ></v-select>
                </v-col>
                <v-col cols="12">
                  <v-textarea
                    solo
                    v-model="description"
                    name="input-7-4"
                    label="Opis oglasa"
                    value=""
                    hint="Opis oglasa"
                    :rules="rules.required"
                  ></v-textarea>
                </v-col>
              </v-row>
            </v-form>
          </v-container>
          <small>*označava obavezno polje</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDialog"> Zatvori </v-btn>
          <v-btn color="blue darken-1" text @click="addAd"> Sačuvaj </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";
import Swal from "sweetalert2";
export default {
  props: {
    dialog: null,
    categories: null,
    cities: null,
    types: null,
  },
  name: "AddAd",
  data() {
    return {
      validAdd: "",
      name: "",
      description: "",
      cityId: "",
      typeId: "",
      categoryId: "",
      price: "",
      rules: {
        required: [(v) => !!v || "Obavezno polje"],
        price: [
          (v) => !!v || "Obavezno polje",
        (v) => parseFloat(v)>0 || "Cijena mora biti pozitivan broj",
        ],
      },
    };
  },
  methods: {
    closeDialog() {
      this.$emit("closeDialog");
      this.$refs.addForm.reset();
    },
    addAd() {
      if (!this.$refs.addForm.validate()) {
        return;
      }
      let data = {
        name: this.name,
        description: this.description,
        categoryId: this.categoryId,
        cityId: this.cityId,
        ownerId: JSON.parse(localStorage.getItem("user")).id,
        statusId: 1,
        typeId: this.typeId,
        price: this.price,
      };
      axios.post("/api/ad", data).then((response) => {
        this.$emit("push", response.data);
        this.$emit("closeDialog");
        Swal.fire({
          icon: "success",
          title: "Oglas uspješno objavljen!",
          showConfirmButton: true,
          confirmButtonText: "Potvrdi",
          timer: 1500,
        });
        this.$refs.addForm.reset();
      });
    },
  },
};
</script>