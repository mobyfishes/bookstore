<template>
  <li class="book-box">
    <div class="book-image">
      <div class="cate-book-list">
        <img
          align="center"
          :src="require('@/assets/images/books/' + book.imageFile)"
          :alt="book.title"
        />
        <div v-if="!book.isPublic"><button class="btn">ReadNow</button></div>
      </div>
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <div class="book-button" @click="addToCart(book)">
      <button class="button">AddToCart</button>
    </div>
  </li>
</template>

<script>
export default {
  name: "categoryBookListItem",
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
  methods: {
    addToCart(book) {
      this.$store.dispatch("addToCart", book);
    },
  },
};
</script>
<style scoped>
.cate-book-list {
  text-align: center;
}

.book-image {
  position: relative;
  padding: 3px;
  width: auto;
  height: 200px;
}

.book-image .btn {
  position: absolute;
  left: 1;
  top: 0;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  background-color: #8fd8d8;
  color: white;
  font-size: 16px;
  padding: 3px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}
.book-image .btn:hover,
.book-image .btn:active {
  color: cornflowerblue;
}
.book-price {
  position: relative;
}

.book-box {
  display: table-column;
  position: relative;
  height: 21rem;
  width: 10rem;
  background-color: whitesmoke;
  padding: 1em;
  gap: 0.25em;
}

.book-author {
  top: 50%;
  font-size: 0.8em;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.book-title {
  padding: 3px;
  position: relative;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
}

.book-button {
  position: absolute;
  bottom: 5px;
}

.button {
  font-size: 1em;
  width: 8rem;
  vertical-align: bottom;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  border-radius: 10px;
}
</style>
