import { useEffect, useState } from "react";
import { StyleSheet, Text, View, ScrollView, Button } from "react-native";

import getAntiSortArray from "../../scripts/getAntiSortArray";

function Game() {
  const [gameArray, setGameArray] = useState([
    {
      title: 0,
      visibility: false,
      finish: false,
    },
    {
      title: 0,
      visibility: false,
      finish: false,
    },
  ]);
  const [size, setSize] = useState(16);
  const [openedCards, setOpenedCards] = useState([]);
  const [gameCount, setGameCount] = useState(0);

  // = = = = = = = = Конструктор
  useEffect(function () {
    restart();
  }, []);

  // = = = = = = = = = Перезагрузка уровня
  function restart() {
    setGameCount(0);
    setOpenedCards([]);

    // Генерируем массив с повторяющими цифрами
    let temp_titleArray = [];
    let k = 0;
    for (let i = 0; i < size; i++) {
      temp_titleArray.push(k);
      if (i % 2 == 1) {
        k += 1;
      }
    }
    // Производим анти сортировку
    temp_titleArray = getAntiSortArray(temp_titleArray);

    // Заполняем игровой массив объектов
    let arr = [];
    let obj = {};
    temp_titleArray.forEach(function (value, index) {
      obj = {};
      obj.title = value;
      obj.visibility = false;
      obj.finish = false;
      arr.push(obj);
    });

    setGameArray(arr);
  }

  // Функция, которая вызывается при нажатии на карточку
  function game_logic(index) {
    openCloseCard(index);
    setGameCount(gameCount + 1);
  }

  // При изменении массива карт запускаем проверку на выигрыш
  useEffect(
    function () {
      isWin(gameArray);
    },
    [gameArray]
  );

  // Функция, которая меняет открытость карты по index
  function changeVisibility(index) {
    let a = gameArray.map(function (value, i) {
      if (i == index) {
        value.visibility = !value.visibility;
      }
      return value;
    });
    setGameArray(a);
  }

  // Функция, которая открывает закрывает карты
  function openCloseCard(index) {
    // Если карточка никакая не открыта
    if (openedCards.indexOf(index) == -1 && openedCards.length < 2) {
      let cards = openedCards.concat([index]);
      setOpenedCards(cards);
      changeVisibility(index);
      // проверка двух карт
      checkTwoCards(cards);
      return;
    }

    // Если карточка открыта, то закрыть её
    if (openedCards.indexOf(index) == 0) {
      let a = openedCards;
      changeVisibility(a[0]);
      a.shift();
      setOpenedCards(a);
      return;
    }
    // Если карточка открыта, то закрыть её
    if (openedCards.indexOf(index) == 1) {
      let a = openedCards;
      changeVisibility(a[1]);
      a.pop();
      setOpenedCards(a);
      return;
    }
    // Если открыто больше чем две карточки, то закрыть первую карточку
    if (openedCards.length == 2) {
      let a = openedCards;
      changeVisibility(a[0]);
      a.shift();
      a.push(index);
      changeVisibility(index);
      setOpenedCards(a);
      // проверка двух карт
      checkTwoCards(a);
      return;
    }
  }

  // Функция, которая сравнивает две карточки
  function checkTwoCards(cards) {
    if (cards.length == 2) {
      let index1 = cards[0];
      let index2 = cards[1];
      if (gameArray[index1].title == gameArray[index2].title) {
        let a = gameArray.map(function (value, i) {
          if (i == index1) {
            value.finish = !value.finish;
            return value;
          }
          if (i == index2) {
            value.finish = !value.finish;
            return value;
          }
          return value;
        });
        setGameArray(a);
        setOpenedCards([]);
      }
    }
  }

  // Функция, которая определяет выйграл ли
  function isWin(arr) {
    let count = 0;
    arr.forEach(function (value, index) {
      if (value.finish === false) {
        ++count;
      }
    });

    if (count <= 1) {
      alert(
        ` Вы прошли игру!\n step = ${gameCount} - количество ходов \n size = ${size} - количество карточек`
      );
    }
  }

  return (
    <ScrollView>
      <Button title="Перезапустить" onPress={restart} color="#dc3545" />
      <Text style={styles.game__count}>Совершено ходов: {gameCount}</Text>
      <View style={styles.game__cards}>
        {gameArray.map(function (value, index) {
          return (
            <View
              style={{ width: `${100 / Math.round(Math.sqrt(size))}%` }}
              key={index}
            >
              <View style={styles.game__button_wrapper}>
                <Button
                  title={
                    gameArray[index].visibility == true
                      ? `${gameArray[index].title}`
                      : "?"
                  }
                  color={
                    gameArray[index].visibility == true ? "#8A2BE2" : "#8FBC8F"
                  }
                  onPress={(event) => game_logic(index)}
                  disabled={value.finish}
                />
              </View>
            </View>
          );
        })}
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  game__cards: {
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "center",
  },
  game__button_wrapper: {
    padding: 8,
  },
  game__count: {
    textAlign: "center",
    fontWeight: "bold",
    padding: 16,
  },
});

export default Game;
