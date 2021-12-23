<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <template v-for="category in $store.state.categories">
        <li
          v-if="category.name === $route.params.name"
          :key="category.categoryId"
          class="button"
        >
          {{ category.name }}
        </li>

        <li v-else :key="category.categoryId">
          <router-link
            class="button unselected-category-button"
            :to="'../category/' + category.name"
          >
            {{ category.name }}
          </router-link>
        </li>
      </template>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "CategoryNav",
};
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  background-color: #e4e7e6;
}

.button.selected-category-button {
  background-color: var(--secondary-background-color);
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: #e4e7e6;
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color);
}
</style>
