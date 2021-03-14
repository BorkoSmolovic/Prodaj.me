<template>
  <v-container fluid grid-list-md class="">
    <v-row class="py-4 primary lighten-4">
      <v-col cols="6">
        <v-row>
          <v-col cols="3">
            <v-text-field
              label="Pretraga"
              hint="Pretraga"
              solo
              @input="search"
              v-model="searchText"
              hide-details
            ></v-text-field>
          </v-col>
          <v-col cols="3">
            <v-select
              @change="search"
              :items="categories"
              v-model="searchCategoryId"
              label="Kategorija"
              item-text="name"
              item-value="id"
              single-line
              clearable
              hide-details
              solo
            ></v-select>
          </v-col>
          <v-col cols="3">
            <v-select
              @change="search"
              :items="types"
              v-model="searchTypeId"
              label="Tip oglasa"
              item-text="name"
              item-value="id"
              single-line
              clearable
              hide-details
              solo
            ></v-select
          ></v-col>
          <v-col cols="3">
            <v-select
              @change="search"
              :items="cities"
              v-model="searchCityId"
              label="Grad"
              item-text="name"
              item-value="id"
              single-line
              clearable
              hide-details
              solo
            ></v-select
          ></v-col>
        </v-row>
      </v-col>
      <v-col cols="3">
        <v-range-slider
          @change="search"
          v-model="priceRange"
          :max="priceTo"
          :min="priceFrom"
          hide-details
          class="align-center"
        >
          <template v-slot:prepend>
            <v-text-field
              :value="priceRange[0]"
              class="mt-0 pt-0"
              hide-details
              single-line
              type="number"
              style="width: 60px"
              @change="
                $set(priceRange, 0, $event);
                search;
              "
              @keypress="search"
            ></v-text-field>
          </template>
          <template v-slot:append>
            <v-text-field
              :value="priceRange[1]"
              class="mt-0 pt-0"
              hide-details
              single-line
              type="number"
              style="width: 60px"
              @change="$set(priceRange, 1, $event)"
              @keypress="search"
            ></v-text-field>
          </template>
        </v-range-slider>
      </v-col>
      <v-col cols="3" class="pl-15">
        <!-- <v-btn @click="test" text class="success">Skepta</v-btn> -->
        <v-btn @click="dialogAdd = true" text class="success">Novi oglas</v-btn>
        <v-btn @click="logout" text class="error mx-6"> Odjavi se</v-btn>
      </v-col>
    </v-row>

    <v-layout row wrap class="pb-5" v-if="!loading">
      <v-col
        cols="3"
        v-for="ad in filteredAds"
        :key="ad.id"
        @click="openDetailsDialog(ad)"
      >
        <v-card max-width="375" class="mb-3 ml-3 mt-3">
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            height="200px"
          ></v-img>

          <v-card-title
            :class="{ success: ad.owner == 1, danger: ad.owner != 1 }"
          >
            {{ ad.name }}
          </v-card-title>

          <v-card-subtitle>
            {{ cities[ad.cityId - 1] ? cities[ad.cityId - 1].name : "" }}
          </v-card-subtitle>

          <v-card-actions>
            <v-spacer />
            <h4>
              <b>{{ ad.price }}€</b>
            </h4>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-layout>
    <AddAd
      :dialog="dialogAdd"
      :cities="cities"
      :categories="categories"
      :types="types"
      @closeDialog="dialogAdd = false"
      @loading="loading = $event"
      @push="onPush($event)"
    />
    <DetailsAd
      :dialog="dialogDetails"
      :cities="cities"
      :categories="categories"
      :types="types"
      :ad="openedAd"
      @closeDialog="dialogDetails = false"
      @loading="loading = $event"
      @edit="onEdit($event)"
      @delete="onDelete($event)"
    />
  </v-container>
</template>
<script>
import axios from "axios";
import AddAd from "@/components/AddAd.vue";
import DetailsAd from "@/components/DetailsAd.vue";
export default {
  name: "Dashboard",
  components: {
    AddAd,
    DetailsAd,
  },
  data() {
    return {
      openedAd: null,
      dialogDetails: false,
      loggedId: -1,
      dialogAdd: false,
      loading: false,
      users: [],
      numberOfUsers: 0,
      ads: [],
      filteredAds: [],
      types: [],
      categories: [],
      cities: [],
      searchText: "",
      searchCategoryId: "",
      searchCityId: "",
      searchTypeId: "",
      searchOwnerId: "",
      statusId: "",
      priceFrom: 0,
      priceTo: 99999,
      priceRange: [0, 99999],
      initialLoad: true,
    };
  },
  watch: {
    ads: function (val) {
      if (val.length > 0 && this.initialLoad) {
        this.calculatePriceRange();
        this.initialLoad = false;
      }
    },
  },
  methods: {
    test() {
      let data = {
        id: 1,
        name: "Prodajem auto izmijenjeno",
        description:
          "Na prodaju GOLF 7 GTI, 2020 godiste, u odličnom stanju. Kao nov!",
        categoryId: 3,
        cityId: 1,
        ownerId: 1,
        statusId: 1,
        typeId: 1,
        price: 20000.0,
      };
      data;
      axios
        .delete("/api/ad/delete/1")
        .then((response) => {
          console.log("uspjesno", response.data);
        })
        .catch((error) => {
          console.log("greska", error.data);
        });
    },
    calculatePriceRange() {
      if (this.ads.length > 0) {
        this.priceFrom = Math.floor(parseFloat(this.ads[0].price));
        this.priceTo = Math.ceil(parseFloat(this.ads[0].price));
        this.priceRange = [
          Math.floor(parseFloat(this.ads[0].price)),
          Math.ceil(parseFloat(this.ads[0].price)),
        ];
      }
      this.ads.forEach((ad) => {
        if (parseFloat(ad.price) < this.priceFrom) {
          this.priceFrom = Math.floor(parseFloat(ad.price));
          this.priceRange[0] = Math.floor(parseFloat(ad.price));
        }
        if (parseFloat(ad.price) > this.priceTo) {
          this.priceTo = Math.ceil(parseFloat(ad.price));
          this.priceRange[1] = Math.ceil(parseFloat(ad.price));
        }
      });
    },
    openDetailsDialog(ad) {
      this.openedAd = ad;
      this.dialogDetails = true;
    },
    onPush() {
      this.loading = true;
      this.getAds();
      this.loading = false;
    },
    onEdit(newAd) {
      this.getAds();
      this.openedAd  = newAd
      this.ads.forEach(ad => {
        if(ad.id == newAd.id){
          this.openedAd.user = ad.user
        }
      })
      this.search();
    },
    onDelete(newAd) {
      console.log(newAd)
      this.getAds();
      this.search();
    },
    search() {
      this.loading = true;
      this.filteredAds = [];
      this.filteredAds = this.ads.filter((ad) => {
        let foundText = false;
        let foundCategory = false;
        let foundType = false;
        let foundCity = false;
        let foundPrice = false;
        let found = false;
        if (
          ad.name.toUpperCase().includes(this.searchText.toUpperCase()) ||
          ad.description.toUpperCase().includes(this.searchText.toUpperCase())
        ) {
          foundText = true;
        }
        if (
          this.searchCategoryId ? ad.categoryId == this.searchCategoryId : true
        ) {
          foundCategory = true;
        }
        if (this.searchCityId ? ad.cityId == this.searchCityId : true) {
          foundCity = true;
        }
        if (this.searchTypeId ? ad.typeId == this.searchTypeId : true) {
          foundType = true;
        }
        if (ad.price >= this.priceRange[0] && ad.price <= this.priceRange[1]) {
          foundPrice = true;
        }
        if (
          foundText &&
          foundCategory &&
          foundType &&
          foundCity &&
          foundPrice
        ) {
          found = true;
        }
        return found;
      });
      this.loading = false;
    },
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/");
    },
    getAds() {
      axios.get("/api/ads").then((response) => {
        this.ads = response.data;
        this.filteredAds = response.data;
        this.calculatePriceRange();
        this.search();
      });
    },
    getTypes() {
      axios.get("/api/types").then((response) => {
        this.types = response.data;
      });
    },
    getCategories() {
      axios.get("/api/categories").then((response) => {
        this.categories = response.data;
      });
    },
    getCities() {
      axios.get("/api/cities").then((response) => {
        this.cities = response.data;
      });
    },
  },
  mounted() {},
  created: function () {
    this.getAds();
    this.getTypes();
    this.getCategories();
    this.getCities();
    let user = JSON.parse(localStorage.getItem("user"));
    if (user != null) {
      this.loggedId = user.id;
    }
  },
};
</script>