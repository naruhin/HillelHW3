Сделанное задание залить на Github и приложить в LMS ссылку.



1. Есть документ со списком URL:

https://drive.google.com/open?id=1wVBKKxpTKvWwuCzqY1cVXCQZYCsdCXTl

Вывести топ 10 доменов которые встречаются чаще всего. В документе могут встречается пустые и недопустимые строки.



2. Сделать дерево с балансировкой, черно-белое. Еще раз перечитать о TreeMap.

https://javarush.ru/groups/posts/2584-osobennosti-treemap



3. Есть фрагмент кода, который при запуске выдает ошибку NullPointerException. Найдите и исправьте ошибку:


    public class Main {
        private static class Ball {
        }

        private static class Board {
            private List balls;

            public Board() {
                List balls = new ArrayList<>();
                balls.add(new Ball());
                balls.add(new Ball());
                balls.add(new Ball());
            }

            public int count() {
                return balls.size();
            }
        }

        public static void main(String[] args) {
            Board board = new Board();
            System.out.println(board.count());
        }
    }