<template>
  <div id="confirmationView">
    <section
      v-if="!orderString && $store.state.cart.numberOfItems == 0"
      class="empty-page"
    >
      <div class="empty-page">
        <p>There is no item in your cart.</p>
        <router-link
          :to="'../category/' + this.$store.state.selectedCategoryName"
          class="button continue-button empty-page-button"
          >Continue Shopping</router-link
        >
      </div>
    </section>
    <section
      v-if="!orderString && $store.state.cart.numberOfItems !== 0"
      class="empty-page"
    >
      <div class="empty-page">
        <p>Your order is processing.</p>
        <router-link
          :to="'../cart/'"
          class="button continue-button empty-page-button"
          >Check Orders</router-link
        >
      </div>
    </section>
    <div v-if="orderString">
      <div>
        <h1>Thanks for shopping</h1>
        <div v-if="orderString">
          <ul>
            <li>
              Confirmation #:
              {{ orderString.order.confirmationNumber }}
            </li>
            <li>
              Time:
              {{ new Date(orderString.order.dateCreated).getHours() }}:{{
                new Date(orderString.order.dateCreated).getMinutes()
              }}-{{ new Date(orderString.order.dateCreated).getDate() }}-{{
                new Date(orderString.order.dateCreated).getMonth() + 1
              }}-{{ new Date(orderString.order.dateCreated).getFullYear() }}
            </li>
          </ul>
          <confirmation-table> </confirmation-table>
          <ul>
            <li>Name: {{ orderString.customer.customerName }}</li>
            <li>Address: {{ orderString.customer.address }}</li>
            <li>Email: {{ orderString.customer.email }}</li>
            <li>Phone: {{ orderString.customer.phone }}</li>
            <li>
              Payment: **** **** ****
              {{
                new String(orderString.customer.ccNumber).substring(
                  new String(orderString.customer.ccNumber).length - 4
                )
              }}&nbsp;&nbsp;
              {{ new Date(orderString.customer.ccExpDate).getMonth() + 1 }}/{{
                new Date(orderString.customer.ccExpDate).getFullYear()
              }}
            </li>
            <router-link
              :to="'../category/' + this.$store.state.selectedCategoryName"
              class="button continue-button"
              >Continue Shopping</router-link
            >
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
export default {
  name: "Confirmation",
  components: { ConfirmationTable },
  computed: {
    orderString: function () {
      const orderString = sessionStorage.getItem("orderDetail");
      return JSON.parse(orderString);
    },
  },
};
</script>

<style scoped>
.empty-page {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-flow: column;
}

.empty-page p {
  font-weight: bold;
  font-size: 40px;
  text-align: center;
  margin: 3em;
  justify-self: center;
}

#confirmationView {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 1em auto;
}

h1 {
  font-size: 4em;
  margin-top: 1em;
  margin-bottom: 1em;
  text-align: center;
}

ul {
  text-align: center;
}
ul > li {
  margin: 0.25em;
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
