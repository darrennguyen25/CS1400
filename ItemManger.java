public class ItemManger {
    public static void main(String[] args) {
        Item<Integer> itemInt = new Item<Integer>(25);
        Item<Character> itemChar = new Item<Character>('u');
  
        itemInt.updateCount(25); 
        itemInt.updateCount(6); 
  
        itemChar.updateCount('z'); 
        itemChar.updateCount('i'); 
     }
 }