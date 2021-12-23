<template>
  <footer class="container">
    <section class="links">
      <p>@BookHub 2020 - 2021</p>
    </section>
    <section class="links">
      <router-link class="router-link-mark2" to="/">about</router-link>&nbsp; |
      &nbsp;
      <router-link class="router-link-mark2" to="/">contact</router-link>&nbsp;
      | &nbsp;
      <router-link class="router-link-mark2" to="/">directions</router-link>
    </section>
    <section class="social-media-icons">
      <div class="right">
        <img src="@/assets/images/site/icons8-facebook-30.png" />
        <img src="@/assets/images/site/icons8-twitter-30.png" />
        <img src="@/assets/images/site/icons8-instagram-30.png" />
      </div>
    </section>
  </footer>
</template>

<script>
export default {
  name: "AppFooter",
};
</script>

<style scoped>
footer {
  background: var(--secondary-background-color);
  display: flex;
  justify-content: space-between;
  gap: 1em;
}

.links,
.social-media-icons {
  display: flex;

  flex-direction: row;
}

.router-link-mark2 {
  color: black;
}

.right {
  display: flex;
}

.social-media-icons {
  text-align: center;
  gap: 0.25em;
}
</style>
