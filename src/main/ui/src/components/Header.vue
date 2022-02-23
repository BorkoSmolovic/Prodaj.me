<template>
  <div class="header" style="height: 12% fixed">
    <b>Prodaj.me</b>
    <h6 class="pt-2">
      Imate li nešto što želite prodati ili kupiti, iznajmiti ili dati u zakup,
      potražiti ili ponuditi posao? Dobrodošli u najveći Crnogorski šoping
      centar.
    </h6>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      name: "",
      user: "",
    };
  },
  methods: {
  
  },
  watch: {
    localStorage: function () {
      this.user = JSON.parse(localStorage.getItem("user"));
      return !this.user == null;
    },
  },
  created: function () {
    this.user = JSON.parse(localStorage.getItem("user"));
    if (!this.user == null) {
      this.name = this.user.firstName;
    }
  },
};
</script>