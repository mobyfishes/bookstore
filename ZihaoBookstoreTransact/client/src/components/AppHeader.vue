<template>
  <header class="container">
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
    />
    <section class="bookstore-logo">
      <router-link to="/">
        <img
          src="@/assets/images/site/bookhub-logo.png"
          alt="BookHub Logo"
          width="150px"
          height="auto"
        />
      </router-link>
    </section>
    <router-link to="/" class="router-link-mark">
      <h1 class="text-logo">BookHub</h1>
    </router-link>
    <header-dropdown-menu></header-dropdown-menu>

    <div class="content">
      <div class="search">
        <input
          type="text"
          class="search__input"
          aria-label="search"
          placeholder="Search..."
        />
        <button class="search__submit" aria-label="submit search">
          <i class="fas fa-search fa-1x"></i>
        </button>
      </div>
    </div>

    <div class="login">
      <a href="#"><span class="icon"></span></a>
    </div>
    <router-link to="/cart">
      <div class="cart">
        <span class="itemCount">({{ $store.state.cart.numberOfItems }})</span>
      </div>
    </router-link>
  </header>
</template>

<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";
export default {
  name: "AppHeader",
  components: { HeaderDropdownMenu },
  component: {
    HeaderDropdownMenu,
  },
};
</script>

<style scoped>
header {
  background: var(--secondary-background-color);
  display: flex;
  padding: 1em;
  gap: 1em;
}
.itemCount:hover {
  color: white;
}

.router-link-mark {
  text-decoration: none;
  color: black;
}

.itemCount {
  margin-left: 1rem;

  position: relative;
  display: flex;
  flex-direction: row;
  vertical-align: middle;
  color: #000;
  text-decoration: none;
}

.cart {
  padding: 1rem;
  display: flex;
  flex-direction: row;
  vertical-align: middle;
  margin-top: 1rem;
  width: 1.5em;
  height: 1.5em;
  margin-right: 1em;
  background: transparent url("../assets/images/header/cart-icon.png") no-repeat
    0;
  background-size: contain;
}

.text-logo {
  font-family: var(--title-font-family);
  padding: 0.5em;
  font-size: 2.5em;
  display: flex;
  height: 100%;
  vertical-align: middle;
}

.content {
  background: var(--secondary-background-color);
  width: 30vw;
  padding: 1em;
  position: relative;
}

.search {
  --size: 40px;
  border: 2px solid black;
  display: flex;
  border-radius: 100px;
  overflow: hidden;
  font-size: 1.25em;
  position: relative;
  width: var(--size);
  height: var(--size);
  transition: width 450ms cubic-bezier(1, 1, 0.3, 1);
  padding: 3px;
}

.search__input {
  border: 0;
  padding: 0.25em 1em;
  flex-grow: 1;
  outline: 0;
  z-index: 2;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  width: 100%;
  background: transparent;
  opacity: 0;
}

.search__submit {
  font-size: 1.5rem;
  margin-left: auto;
  background: 0;
  border: 0;
  cursor: pointer;
  border-radius: 50%;
  transition: background 200ms ease-out;
  width: calc(var(--size) - 10px);
  height: calc(var(--size) - 10px);
}

.search:focus-within {
  width: 100%;

  cursor: pointer;
}
.search__input {
  opacity: 1;

  z-index: initial;
  cursor: initial;
  width: calc(100% - var(--size));

  cursor: pointer;
}

.search__submit {
  color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);

  cursor: pointer;
}

header .login .icon {
  margin-left: 1em;
  position: relative;
  display: inline-block;
  vertical-align: middle;
  width: 4em;
  height: 85%;
  background: transparent url("../assets/images/header/login-icon.png")
    no-repeat 0;
}

@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}
</style>
