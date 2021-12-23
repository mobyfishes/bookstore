<template>
  <table>
    <tr v-for="(item, index) in orderString.lineItems" :key="item.productId">
      <td>
        {{ orderString.books[index].title }}
      </td>
      <td>{{ item.quantity }}</td>
      <td>$ {{ (orderString.books[index].price * item.quantity) / 100 }}</td>
    </tr>
    <tr>
      <td>Total:</td>
      <td>{{ orderString.lineItems.length }}</td>
      <td>${{ totalPrice / 100 }}</td>
    </tr>
  </table>
</template>

<script>
export default {
  name: "ConfirmationTable",
  computed: {
    orderString: function () {
      const orderString = sessionStorage.getItem("orderDetail");
      return JSON.parse(orderString);
    },
    totalPrice: function () {
      const orderString2 = sessionStorage.getItem("orderDetail");
      const orderString3 = JSON.parse(orderString2);
      let sum = 0;
      for (let i = 0; i < orderString3.lineItems.length; i++) {
        sum += orderString3.books[i].price * orderString3.lineItems[i].quantity;
      }
      return sum;
    },
  },
};
</script>

<style scoped>
table {
  border: 1px black solid;
  width: fit-content;
  margin-top: 1em;
  margin-bottom: 1em;
  margin-left: auto;
  margin-right: auto;
}

td {
  display: table-cell;
  padding: 0.5em 0.5em;
  background-color: white;
  vertical-align: middle;
}

tr:nth-child(even) > td {
  background-color: lightgray;
}

td:nth-child(1) {
  text-align: left;
}

td:nth-child(2) {
  text-align: center;
}

td:nth-child(3) {
  text-align: right;
}
</style>
