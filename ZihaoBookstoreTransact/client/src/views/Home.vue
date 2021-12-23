<template>
  <div class="home-page">
    <section class="welcome-text flow-content cat-container">
      <h2 style="background-color: #8fd8d8; text-align: center">Categories</h2>

      <ul>
        <li>
          <router-link class="button" to="../category/Classics"
            >Classic</router-link
          >
        </li>
      </ul>
      <ul>
        <li>
          <router-link class="button" to="../category/Fantasy"
            >Fantasy</router-link
          >
        </li>
      </ul>
      <ul>
        <li>
          <router-link class="button" to="../category/Mystery"
            >Mystery</router-link
          >
        </li>
      </ul>
      <ul>
        <li>
          <router-link class="button" to="../category/Romance"
            >Romance</router-link
          >
        </li>
      </ul>
    </section>
    <section class="containerElement">
      <home-category-list></home-category-list>
      <router-link class="button more-button" to="../category/Classics"
        >MORE BOOKS!</router-link
      >
    </section>
  </div>
</template>

<script>
import HomeCategoryList from "@/components/HomeCategoryList";
export default {
  name: "Home",
  components: { HomeCategoryList },
};
</script>

<style scoped>
.home-page {
  display: flex;
}

.welcome-text {
  width: 20em;
}

.containerElement {
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.button {
  padding: 10px;
  height: 5%;
  width: 80%;
  margin-left: 2rem;
  text-align: center;
  position: relative;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  cursor: pointer;
}
.button::inactive-selection {
  background: #bababa;
}

.more-button {
  padding: 10px;
  height: 5%;
  width: 20%;
  position: relative;
  margin-left: 40%;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
}
</style>
