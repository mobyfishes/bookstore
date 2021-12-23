<template>
  <div class="cart-page">
    <section v-if="$store.state.cart.numberOfItems === 0" class="empty-page">
      <div class="empty-page">
        <p>Your cart is empty.</p>
        <router-link
          :to="'../category/' + this.$store.state.selectedCategoryName"
          class="button continue-button empty-page-button"
          >Continue Shopping</router-link
        >
      </div>
    </section>
    <div v-else class="non-empty-page">
      <div class="books-check">
        <cart-table></cart-table>
        <div class="clear-box">
          <button class="button link-like-button" @click="clearCart()">
            Clear the Cart
          </button>
        </div>
      </div>
      <div class="cart-button">
        <p v-if="this.$store.state.cart.numberOfItems === 1" class="check-box">
          -Total number of item in cart:
          {{ this.$store.state.cart.numberOfItems }}<br />
          -Total price: ${{ this.$store.state.cart.subtotal / 100 }}<br />
        </p>
        <p v-else class="check-box">
          -Total number of items in cart:
          {{ this.$store.state.cart.numberOfItems }}<br />
          -Total price: ${{ this.$store.state.cart.subtotal / 100 }}<br />
        </p>
        <router-link :to="'../checkout/'" class="button check-button"
          >Proceed to Checkout</router-link
        >
        <router-link
          :to="'../category/' + this.$store.state.selectedCategoryName"
          class="button continue-button"
          >Continue Shopping</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import CartTable from "@/components/CartTable";
export default {
  name: "Cart",
  components: { CartTable },
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    },
  },
};
</script>

<style scoped>
.empty-page {
  with: 60em;
  display: flex;
  flex-direction: column;
}
.empty-page p {
  font-weight: bold;
  font-size: 40px;
  text-align: center;
  margin: 3em;
  justify-self: center;
}

.empty-page-button {
  align-self: center;
  padding: 3em;
  margin-bottom: 3em;
}

.non-empty-page {
  display: flex;
  flex-direction: row;
}

.check-box {
  font-size: 20px;
  margin-bottom: 2em;
  font-weight: bold;
}

.books-check {
  justify-content: right;
}

.continue-button {
  width: 13em;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  padding: 0.3em;
  text-align: center;
}

.check-button {
  padding: 1em;
  margin: 1em;
  width: 20em;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  font-size: 20px;
  text-align: center;
  font-weight: bold;
}
.cart-button {
  display: flex;
  flex-direction: column;
  padding: 1rem;
  justify-content: flex-start;
  align-items: center;
}

.link-like-button {
  padding: 1em;
  margin: 1em;

  height: 3em;
  border-radius: 500px;
  background: #cacaca;
  box-sizing: border-box;
}
</style>
