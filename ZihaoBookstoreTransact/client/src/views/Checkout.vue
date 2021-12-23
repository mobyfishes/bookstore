<template>
  <div class="checkout-page">
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
    <div class="checkout-page-body" v-if="!cart.empty">
      <div class="information-page">
        <form @submit.prevent="submitOrder">
          <div>
            <label for="name">Name: </label>
            <input
              type="text"
              size="40"
              id="name"
              name="name"
              v-model.lazy="$v.name.$model"
            />
          </div>
          <template v-if="$v.name.$error">
            <span class="error" v-if="!$v.name.required">Name is required</span>
            <span class="error" v-else-if="!$v.name.minLength">
              Name must have at least
              {{ $v.name.$params.minLength.min }} letters.
            </span>
            <span class="error" v-else-if="!$v.name.maxLength">
              Name can have at most
              {{ $v.name.$params.maxLength.max }} letters.
            </span>
            <span class="error" v-else> An unexpected error occurred. </span>
          </template>
          <!-- TODO: Add address input and validation messages -->

          <div>
            <label for="name">Address: </label>
            <input
              type="text"
              size="40"
              id="address"
              name="address"
              v-model.lazy="$v.address.$model"
            />
          </div>
          <template v-if="$v.address.$error">
            <span class="error" v-if="!$v.address.required"
              >Address is required</span
            >
            <span class="error" v-else-if="!$v.address.minLength">
              Address must have at least
              {{ $v.address.$params.minLength.min }} letters.
            </span>
            <span class="error" v-else-if="!$v.address.maxLength">
              Address can have at most
              {{ $v.address.$params.maxLength.max }} letters.
            </span>
            <span class="error" v-else> An unexpected error occurred. </span>
          </template>

          <div>
            <label for="phone">Phone: </label>
            <input
              class="textField"
              type="text"
              size="40"
              id="phone"
              name="phone"
              v-model.lazy="$v.phone.$model"
            />
          </div>
          <template v-if="$v.phone.$error">
            <span class="error" v-if="!$v.phone.required"
              >Phone is required</span
            >
            <span class="error" v-else-if="!$v.phone.phone"
              >Please enter a valid phone number</span
            >
            <span class="error" v-else> An unexpected error occurred. </span>
          </template>

          <div>
            <label for="email">Email: </label>
            <input
              type="text"
              size="40"
              id="email"
              name="email"
              v-model.lazy="$v.email.$model"
            />
          </div>
          <template v-if="$v.email.$error">
            <span class="error" v-if="!$v.email.required"
              >Email address is required</span
            >
            <span class="error" v-else-if="!$v.email.email"
              >Please enter a valid Email address</span
            >
            <span class="error" v-else> An unexpected error occurred. </span>
          </template>

          <div>
            <label for="ccNumber">Credit card</label>
            <input
              type="text"
              size="40"
              id="ccNumber"
              name="ccNumber"
              v-model.lazy="$v.ccNumber.$model"
            />
          </div>
          <template v-if="$v.ccNumber.$error">
            <span class="error" v-if="!$v.ccNumber.required"
              >Card Number is required</span
            >
            <span class="error" v-else-if="!$v.ccNumber.phone"
              >Please enter a valid Card number</span
            >
            <span class="error" v-else> An unexpected error occurred. </span>
          </template>

          <div class="select-card">
            <label>Exp Date</label>
            <select v-model="ccExpiryMonth">
              <option
                v-for="(month, index) in months"
                :key="index"
                :value="index"
              >
                {{ month }}
              </option>
            </select>
            <select v-model="ccExpiryYear">
              <option
                v-for="(year, index) in 20"
                :key="index"
                :value="index + 2021"
              >
                {{ yearFrom(index) }}
              </option>
            </select>
          </div>
          <input
            type="submit"
            name="submit"
            class="button complete-button"
            :disabled="checkoutStatus == 'PENDING'"
            value="Complete Purchase"
          />
        </form>
      </div>

      <div class="processing-page">
        <div class="confirmation-page">
          <p align="right">The subtotal: <br />${{ cart.subtotal / 100 }}</p>
          <p align="right">The surcharge: <br />${{ cart.surcharge / 100 }}</p>
          <br />
          <div class="total-information">
            Your credit card will be charged: <br />
          </div>
          <p class="price-information" align="right">
            ${{ (cart.subtotal + cart.surcharge) / 100 }}
          </p>
        </div>

        <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
          <div v-if="checkoutStatus == 'ERROR'">
            Error: Please fix the problems above and try again.
          </div>

          <div v-else-if="checkoutStatus == 'PENDING'">Processing...</div>

          <div v-else-if="checkoutStatus == 'OK'">Order placed...</div>

          <div v-else>Please fill validate information.</div>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
import { required, minLength, maxLength } from "vuelidate/lib/validators";

import isMobilePhone from "validator/lib/isMobilePhone";
import isEmail from "validator/lib/isEmail";
import isCreditCard from "validator/lib/isCreditCard";

const phone = (value) => isMobilePhone(value, "en-US");
const email = (value) => isEmail(value);
const ccNumber = (value) => isCreditCard(value);

export default {
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth(),
      ccExpiryYear: new Date().getFullYear(),
      checkoutStatus: "",
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    address: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      phone,
    },
    email: {
      required,
      email,
    },
    ccNumber: {
      required,
      ccNumber,
    },
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
    },
  },

  methods: {
    submitOrder() {
      console.log("Submit order");
      this.$v.$touch();
      if (this.$v.$error) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.checkoutStatus = "OK";
          console.log("month: " + this.ccExpiryMonth);
          this.$store
            .dispatch("placeOrder", {
              name: this.name,
              address: this.address,
              phone: this.phone,
              email: this.email,
              ccNumber: this.ccNumber,
              ccExpiryMonth: this.ccExpiryMonth,
              ccExpiryYear: this.ccExpiryYear,
            })
            .then(() => {
              this.checkoutStatus = "OK";
              this.$router.push({ name: "confirmation" });
            })
            .catch((reason) => {
              this.checkoutStatus = "SERVER_ERROR";
              console.log("Error placing order", reason);
            });
        }, 1000);
      }
    },
    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
  },
};
</script>

<style scoped>
.checkout-page {
  background: rgba(105, 100, 100, 0.5);
  color: black;
  background: whitesmoke;
}

.information-page {
  display: inline-block;
  padding: 3em;
}

.processing-page {
  display: inline-block;
  margin-top: 1em;
}

.checkout-page-body {
  display: flex;
  padding: 1em;
  justify-content: space-evenly;
}

form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  border-radius: 500px;
  background-color: white;
  margin-left: 0.5em;
  margin-bottom: 1em;
}

form > .error {
  margin-top: -1.6em;
  margin-bottom: 1.5em;
  color: red;
  text-align: right;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.complete-button {
  width: 13em;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  padding: 0.3em;
  text-align: center;
  margin-top: 2em;
  margin-left: 50%;
  font-weight: bold;
}

.confirmation-page {
  vertical-align: middle;
}
.total-information {
  font-size: 2em;
}

.price-information {
  font-size: 4em;
}
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

.continue-button {
  width: 13em;
  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  padding: 0.3em;
  text-align: center;
}
</style>
