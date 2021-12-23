import Vue from "vue";
import Vuex from "vuex";
import ApiService from "@/services/ApiService";
import { ShoppingCart } from "@/models/ShoppingCart";

Vue.use(Vuex);
export const CART_STORAGE_KEY = "cart";
export const ORDER_DETAIL_STORAGE_KEY = "orderDetail";

export default new Vuex.Store({
  state: {
    categories: [],
    selectedCategoryName: "",
    selectedCategoryBooks: [],
    cart: new ShoppingCart(),
    orderDetails: null,
  },
  mutations: {
    SET_CATEGORIES(state, newCategories) {
      state.categories = newCategories;
    },
    ADD_TO_CART(state, book) {
      state.cart.addItem(book, 1);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    SET_SELECTED_CATEGORY_BOOKS(state, newBooks) {
      state.selectedCategoryBooks = newBooks;
    },
    SET_CATEGORY(state, name) {
      state.selectedCategoryName = name;
    },
    UPDATE_CART(state, { book, quantity }) {
      state.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    CLEAR_CART(state) {
      localStorage.removeItem(CART_STORAGE_KEY);
      state.cart.clear();
    },
    REMOVE_BOOK(state, { book, quantity }) {
      state.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    SET_CART(state, shoppingCart) {
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
      let newCart = new ShoppingCart();
      shoppingCart.items.forEach((item) => {
        newCart.addItem(item.book, item.quantity);
      });
      state.cart = newCart;
    },
    SET_ORDER_DETAILS(state, newOrder) {
      state.orderDetails = newOrder;
      sessionStorage.setItem(
        ORDER_DETAIL_STORAGE_KEY,
        JSON.stringify(newOrder)
      );
    },

    CLEAR_ORDER_DETAILS(state) {
      sessionStorage.removeItem(ORDER_DETAIL_STORAGE_KEY);
      state.orderDetails = null;
    },
  },
  actions: {
    clearCart(context) {
      context.commit("CLEAR_CART");
    },

    removeBook(context, { book, quantity }) {
      context.commit("REMOVE_BOOK", { book, quantity });
    },

    setCategoryName(context, name) {
      context.commit("SET_CATEGORY", name);
    },

    fetchCategories(context) {
      ApiService.fetchCategories()
        .then((categories) => {
          context.commit("SET_CATEGORIES", categories);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },

    addToCart(context, book) {
      context.commit("ADD_TO_CART", book);
    },

    updateCart(context, { book, quantity }) {
      context.commit("UPDATE_CART", { book, quantity });
    },

    placeOrder(context, customerForm) {
      return ApiService.placeOrder({
        cart: context.state.cart,
        customerForm: customerForm,
      }).then((orderDetails) => {
        context.commit("CLEAR_CART");
        context.commit("SET_ORDER_DETAILS", orderDetails);
      });
    },

    fetchSelectedCategoryBooks(context) {
      return ApiService.fetchSelectedCategoryBooks(
        context.state.selectedCategoryName
      )
        .then((selectedBooks) => {
          for (let bookIndex in selectedBooks) {
            let book = selectedBooks[bookIndex];
            book["imageFile"] = book.title.replace(/ /g, "-") + ".jpg";
          }
          context.commit("SET_SELECTED_CATEGORY_BOOKS", selectedBooks);
        })
        .catch((reason) => {
          console.log("Error SET_SELECTED_CATEGORY_BOOKS", reason);
          throw reason;
        });
    },
  },
});
