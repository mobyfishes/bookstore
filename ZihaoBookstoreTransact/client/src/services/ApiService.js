const portFrom = {
  "http:": ":8080",
  "https:": ":8443",
};

const apiUrl =
  location.protocol +
  "//" +
  location.hostname +
  portFrom[location.protocol] +
  process.env.BASE_URL +
  "api";
export default {
  fetchCategories() {
    const url = apiUrl + "/categories";
    return fetch(url)
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        throw new Error("Network response was not ok.");
      })
      .catch((reason) => {
        console.log("Error fetching category data", reason);
      });
  },

  fetchSelectedCategoryBooks(categoryName) {
    const url = apiUrl + "/categories/name/" + categoryName + "/books";
    return fetch(url)
      .then((response) => {
        if (response.ok) {
          if (response.headers.get("content-length") <= 2) {
            throw new Error("No such category.");
          }
          return response.json();
        }
        throw new Error("Network response was not ok.");
      })
      .catch((reason) => {
        console.log("Error fetching category data", reason);
        throw reason;
      });
  },

  placeOrder(order) {
    return fetch(`${apiUrl}/orders`, {
      method: "POST",
      body: JSON.stringify(order),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((stream) => {
      if (stream.ok) {
        return stream.json();
      }
      throw new Error("Network response was not ok.");
    });
  },
};
