<template>
  <section class="category-images container">
    <div class="cate category-image-items">
      <div>
        <img align="left" src="../assets/images/categories/best.png" />
        <p class="cate">Best</p>
        <br />
        <br />
        <br />
        <br />
        <br />

        <p class="cate">Sale</p>
      </div>
      <div class="book">
        <img src="../assets/images/books/covid.jpg" />
        <h3>The Truth About Covid</h3>
        <br />
        <p>Joseph Mercola</p>
      </div>

      <div class="book">
        <img align="left" src="../assets/images/books/wildland.jpg" />
        <h3>Wildland The Making of America's Fury</h3>
        <br />
        <p>Evan Osnos</p>
      </div>

      <div class="book">
        <img align="left" src="../assets/images/books/9781982182915_l.jpg" />
        <h3>Peril</h3>
        <br />
        <p>Bob Woodward, Robert Costa</p>
      </div>
    </div>

    <div class="cate category-image-items">
      <div>
        <img src="../assets/images/categories/popular.png" />
        <p class="cate">Most</p>

        <p style="font-size: 20px" class="cate">popular</p>
      </div>
      <div class="book">
        <img align="left" src="../assets/images/books/9780593086889_l.jpg" />
        <h3>
          A Hunter-Gatherer's Guide to the 21st Century: Evolution and the
          Challenges of Modern Life
        </h3>
        <br />
        <p>Heather Heying, Bret Weinstein</p>
      </div>

      <div class="book">
        <img align="left" src="../assets/images/books/9781501135972_l.jpg" />
        <h3>American Marxism</h3>
        <br />
        <p>Mark R Levin</p>
      </div>

      <div class="book">
        <img align="left" src="../assets/images/books/9780593203224_l.jpg" />
        <h3>Change Sings: A Children's Anthem</h3>
        <br />
        <p>Amanda Gorman, Loren Long (Illustrator)</p>
      </div>
    </div>

    <div class="cate category-image-items">
      <div>
        <img src="../assets/images/categories/deals.png" />
        <p style="font-size: 25px" class="cate">Deals</p>
      </div>
      <div class="book">
        <img align="left" src="../assets/images/books/9781788282369_l.jpg" />
        <h3>1984</h3>
        <br />
        <p>George Orwell</p>
      </div>

      <div class="book">
        <img align="left" src="../assets/images/books/9781529109443_l.jpg" />
        <h3>The Boy, the Mole, the Fox and the Horse</h3>
        <br />
        <p>Charlie Mackesy</p>
        <p>$3.99</p>
      </div>

      <div class="book">
        <img align="left" src="../assets/images/books/9781250220257_l.jpg" />
        <h3>Apples Never Fall</h3>
        <br />
        <p>Liane Moriarty</p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "HomeCategoryList",
};
</script>

<style scoped>
ul {
  display: flex;
  align-items: stretch;
  justify-content: space-between;
  width: 100%;
  background: #cacaca;
  margin: 0;
  padding: 0;
}

.catebook img {
  float: left;
  width: auto;
  height: 200px;
  margin-left: 1em;
}

.catebook h3 {
  color: black;
  font-size: 1.3rem;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.catebook p {
  color: black;
  font-size: 0.1em;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.catebook button {
  display: inline-block;
  font-size: 0.6rem;
  position: absolute;
  bottom: 0;
  right: 1;
  width: 5.5rem;
  height: 2rem;
  margin-left: 0.1rem;

  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
}

.books img {
  float: left;
  width: auto;
  height: 200px;
  margin-left: 1em;
}

.books h3 {
  font-size: 1.5rem;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.books p {
  font-size: 0.1em;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

.books button {
  font-size: 0.8em;
  width: 5.5rem;
  height: 30px;
  left: 458px;
  top: 343px;
  margin-top: 0.3rem;
  display: table-cell;
  vertical-align: bottom;

  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
  border-radius: 10px;
}

p.cate {
  font: bold 2rem "Helvetica Neue", Helvetica, Arial, sans-serif;
  height: 2rem;
  margin-left: 0.9rem;
  vertical-align: middle;
  line-height: 3rem;
  padding: 1px;
}

.book img {
  float: left;
  width: auto;
  height: 200px;
  margin-left: 1em;
}

.book {
  flex: 33%;
  border-radius: 5px;
  width: 15em;
  height: 200px;
  overflow: hidden;
  position: relative;
}

.book h3 {
  height: 5rem;
  padding: 5px;
  font-size: 1.5rem;
  white-space: initial;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.book p {
  top: 50%;
  padding: 5px;
  font-size: 0.7em;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}

.book button {
  padding: 1px;
  display: inline-block;
  font-size: 0.8rem;
  position: absolute;
  right: 1rem;
  bottom: 0;
  width: 5.5rem;
  height: 2rem;
  margin-left: 0.1rem;

  border-radius: 500px;
  background: #2cd8ae;
  border: 1px solid #2cdb9e;
  box-sizing: border-box;
}

div.cate {
  width: 65em;
  height: 14em;
  min-height: 200px;

  padding: 0.5rem;
  margin-top: 0em;
  margin-bottom: 0.5em;
  margin-left: 0.5em;
  margin-left: 0.5em;
  background: linear-gradient(
    180deg,
    rgba(143, 216, 216, 0.56) 99.99%,
    rgba(184, 253, 241, 0) 100%
  );
  border: 1px solid #000000;
  box-sizing: border-box;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
  border-radius: 10px;
}

.category-image-items {
  display: flex;
  position: relative;
  width: 50%;
}
</style>
