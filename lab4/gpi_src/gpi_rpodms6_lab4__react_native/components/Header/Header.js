import { StyleSheet, Text, View } from "react-native";

function Header() {
  return (
    <View style={styles.header__wrapper}>
      <View style={styles.header__line}></View>
      <View style={styles.header__content}>
        <Text style={styles.header__text}>Игра "Память"</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  header__wrapper: {
    backgroundColor: "#06c",
  },
  header__line: {
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    height: 32,
  },
  header__content: {
    marginHorizontal: 16,
    marginVertical: 16,
  },
  header__text: {
    color: "white",
  },
});

export default Header;
