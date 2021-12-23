<template>
  <div class="notfound-page">
    <h1>The page you were looking for was not found.</h1>
    <router-link :to="'../home/'" class="button continue-button"
      >Continue Shopping</router-link
    >
  </div>
</template>

<script>
export default {
  name: "NotFound",
};
</script>

<style scoped>
.notfound-page {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-flow: column;
}

.notfound-page h1 {
  margin-top: 2em;
  margin-bottom: 1em;
  text-align: center;
}
.continue-button {
  margin-top: 1em;
  width: 13em;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  padding: 0.3em;
  text-align: center;
  margin-bottom: 3em;
}
</style>
