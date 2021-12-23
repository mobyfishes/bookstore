<template>
  <div>
    <category-nav></category-nav>
    <category-book-list :books="$store.state.selectedCategoryBooks">
    </category-book-list>
  </div>
</template>

<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";

export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
  },
  created: function () {
    const self = this;
    this.$store.dispatch("setCategoryName", this.$route.params.name);
    this.$store.dispatch("fetchSelectedCategoryBooks").catch(function () {
      self.$router.push("/404");
    });
  },
};
</script>

<style scoped>
ul {
  width: 100%;
  background: #cacaca;
  margin: 0;
  padding: 1em;
}
</style>
