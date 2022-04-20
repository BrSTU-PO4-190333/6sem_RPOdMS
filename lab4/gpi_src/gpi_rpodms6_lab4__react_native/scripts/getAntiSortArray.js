import getRandomArbitrary from "./getRandomArbitrary";

// = = = = = = = = Функция, которая производит анти сортировку
function getAntiSortArray(arr = []) {
  let length = arr.length;
  let index;
  let temp;
  for (let i = 0; i < length; ++i) {
    for (let j = 0; j < length; ++j) {
      index = Math.round(getRandomArbitrary(0, length - 1));
      temp = arr[index];
      arr[index] = arr[j];
      arr[j] = temp;
    }
  }
  return arr;
}

export default getAntiSortArray;
