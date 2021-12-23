<template>
  <ul id="book-boxes">
    <category-book-list-item
      tag="li"
      v-for="book in books"
      :key="book.bookId"
      :book="book"
      >{{ book.bookId }}</category-book-list-item
    >
  </ul>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  props: {
    books: {
      type: Array,
      required: true,
    },
  },
  components: {
    CategoryBookListItem,
  },
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 5em;
}
</style>
