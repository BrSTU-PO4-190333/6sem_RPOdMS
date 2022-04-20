// = = = = = = = = Функция которое генерирует число в пределах [float min - float max)
function getRandomArbitrary(min, max) {
  return Math.random() * (max - min) + min;
}

export default getRandomArbitrary;
