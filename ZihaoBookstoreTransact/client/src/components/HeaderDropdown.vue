<template>
  <div class="header-dropdown">
    <button class="button categories-button">
      Categories<img src="../assets/images/site/down.png" />
    </button>
    <ul>
      <router-link
        v-for="category in $store.state.categories"
        :key="category.categoryId"
        :to="`../category/${category.name}`"
        tag="li"
        >{{ category.name }}</router-link
      >
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu",
};
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
  padding: 0.5em;
  display: inline-block;
  width: 10em;
  margin-top: 1em;
  margin-left: 0em;
  background: #e4e7e6;
  border: 1px solid #bababa;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 500px;
}

.categories-button img {
  left: 50%;
  margin-left: 35px;
  top: 50%;
  margin-top: -20px;
  position: absolute;
}

.header-dropdown ul {
  margin-left: 1em;
  background-color: white;
  color: black;
  display: none;
}

.header-dropdown li {
  text-align: center;
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: linear-gradient(
    180deg,
    rgba(143, 216, 216, 0.56) 99.99%,
    rgba(184, 253, 241, 0) 100%
  );
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8em;
  z-index: 1;
  cursor: pointer;
}
</style>
