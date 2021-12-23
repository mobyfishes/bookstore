<template>
  <div class="cart-table">
    <ul>
      <li class="cart-heading">
        <div class="cart-heading-book">Book</div>
        <div class="cart-heading-quantity">Quantity</div>
        <div class="cart-heading-amount">Amount</div>
      </li>

      <li v-for="item in $store.state.cart.items" :key="item.book.bookId">
        <div class="cart-book-image">
          <img
            align="center"
            :src="require('@/assets/images/books/' + item.book.imageFile)"
            :alt="item.book.title"
            witdth="100px"
            height="auto"
          />
        </div>
        <div class="cart-book-title">{{ item.book.title }}</div>
        <div class="cart-book-price">${{ item.book.price / 100 }}</div>
        <div class="cart-book-quantity">
          <span class="row-book-quantity">{{ item.quantity }}</span>
          <button
            class="icon-button inc-button"
            @click="updateCart(item.book, item.quantity + 1)"
          >
            <i class="fas fa-plus-circle"></i>
          </button>
          <button
            class="icon-button inc-button"
            @click="updateCart(item.book, item.quantity - 1)"
          >
            <i class="fas fa-minus-circle"></i>
          </button>
        </div>
        <div class="subtotal">
          ${{ (item.book.price * item.quantity) / 100 }}
          <button class="remove-book" @click="removeBook(item.book, 0)">
            remove
          </button>
        </div>

        <div class="row-spe"></div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "CartTable",
  methods: {
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity });
    },
    removeBook(book, quantity) {
      this.$store.dispatch("removeBook", { book, quantity });
    },
  },
};
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) max-content max-content max-content;
  background: #e4e7e6;
  row-gap: 1em;
  padding: 1em;
  margin: 0 auto;
}
.remove-book {
  justify-content: flex-end;
  display: flex;
  width: 4em;
  margin-top: 8em;
  cursor:pointer;
}

ul,
li {
  display: contents;
}

.row-spe {
  grid-column: 1 / -1;
  background: black;
  height: 2px;
}

.cart-heading {
  background-color: #8fd8d8;
}

.cart-heading > * {
  background: #cacaca;
}

.cart-heading-book {
  grid-column: 1/ 3;
  padding: 0 1em;
}
.cart-heading-quantity {
  grid-column: 3 / 5;
  padding: 0 1em;
}

.cart-heading-amount {
  grid-column: -2 / -1;
  padding: 0 1em;
}

.cart-book-image {
  padding: 0 1em;
}
.cart-book-title {
  padding: 0 1em;
}
.cart-book-price {
  padding: 0 1em;
}
.cart-heading-quantity {
  padding: 0 1em;
}
.subtotal {
  padding: 0 1rem;
}

.cart-book-image img{
  width:85px;
}

.icon-button {
  background-color: transparent;
  font-size: 1rem;
  border: none;
  margin-left: 0.3em;
  cursor:pointer;
}
.icon-button:hover {
  color: #8fd8d8;
}
</style>
