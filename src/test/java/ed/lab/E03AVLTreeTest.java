package ed.lab;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

class E03AVLTreeTest {

    private static final Map<String, BiFunction<E03AVLTree<Integer>, Integer, Integer>> OPERATORS = Map.of(
            "insert", (tree, val) -> {
                tree.insert(val);
                return null;
            },
            "delete", (tree, val) -> {
                tree.delete(val);
                return null;
            },
            "search", (tree, val) -> tree.search(val),
            "height", (tree, val) -> tree.height(),
            "size",   (tree, val) -> tree.size()
    );

    @ParameterizedTest
    @MethodSource("testArguments")
    void test(List<AVLOperations> operations) {
        E03AVLTree<Integer> avlTree = new E03AVLTree<>(Integer::compare);

        for (AVLOperations operation : operations) {
            OPERATORS.get(operation.operationName().toLowerCase())
                    .apply(avlTree, operation.argument());
        }
    }

    private static Stream<List<AVLOperations>> testArguments() {
        return Stream.of(
                List.of(
                        new AVLOperations("search", 5),     // null (árbol vacío)
                        new AVLOperations("insert", 5),
                        new AVLOperations("insert", 3),
                        new AVLOperations("insert", 1),     // rebalancea
                        new AVLOperations("search", 5),     // 5
                        new AVLOperations("search", 1),     // 1
                        new AVLOperations("size", null),    // 3
                        new AVLOperations("height", null),  // 2
                        new AVLOperations("delete", 3),
                        new AVLOperations("search", 3),     // null
                        new AVLOperations("insert", 4)      // rebalancea
                )
        );
    }

    public record AVLOperations(String operationName, Integer argument) {}
}