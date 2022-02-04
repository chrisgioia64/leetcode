package base.training;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Streams {

    public static class ProductDetails {
        private String productName;
        private double productPrice;

        public ProductDetails(String productName, double productPrice) {
            this.productName = productName;
            this.productPrice = productPrice;
        }

        @Override
        public String toString() {
            return "ProductDetails{" +
                    "productName='" + productName + '\'' +
                    ", productPrice=" + productPrice +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 5, 1, 4, 3, 9);

        List<Integer> list3 = list.stream().map(x -> x * x * x).collect(Collectors.toList());

        System.out.println(list3);

        List<String> oddEven = list.stream()
                .map(x -> x % 2 == 0 ? "Even" : "Odd").collect(Collectors.toList());

        System.out.println(oddEven);

        oddEven.forEach( (x) -> System.out.println(x));

        Random rand = new Random();
        rand.ints().map( (x) -> x + 1);

        List<ProductDetails> productDetails = new LinkedList<>();
        productDetails.add(new ProductDetails("pen1", 10));
        productDetails.add(new ProductDetails("pen2", 15));
        productDetails.add(new ProductDetails("pen3", 5));

        List<ProductDetails> result1 = productDetails.stream()
                .filter((x -> x.productName.equals("pen1")))
                        .collect(Collectors.toList());
        System.out.println(result1);

        List<ProductDetails> result2 = productDetails.stream()
                .filter((x -> x.productPrice > 5))
                .collect(Collectors.toList());
        System.out.println(result2);

        List<ProductDetails> result3 = productDetails.stream()
                .filter((x -> x.productPrice < 15))
                .collect(Collectors.toList());
        System.out.println(result3);

        productDetails.forEach((x) -> {
            if (x.productName.equals("pen1")) {
                System.out.println(x.productName);
            }});

    }
}
