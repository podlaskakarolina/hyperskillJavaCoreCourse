package org.java.com.kpodlaska.flashcards.production;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    //lists
    private static final List<Card> CARD_LIST = new ArrayList<>();
    private static final List<String> LOG_LIST = new ArrayList<>();
    //scanner
    private static final Scanner scanner = new Scanner(System.in);
    //random
    private static final Random random = new Random();
    //print message
    private static final String MAIN_MENU_MESSAGE = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
    //print->add
    private static final String ASK_CARD_TERM = "The card:";
    private static final String ASK_CARD_DEFINITION = "The definition of the card:";
    private static final String CARD_HAS_BEEN_ADDED = "The pair (\"%s\":\"%s\") has been added.%n";
    private static final String DEFINITION_ALREADY_EXISTS = "The definition \"%s\" already exists.%n";
    private static final String THE_CARD_ALREADY_EXISTS = "The card \"%s\" already exists.%n";
    //print->remove
    private static final String CANT_REMOVE_THIS_CARD = "Can't remove \"%s\": there is no such card.%n";
    private static final String CARD_HAS_BEEN_REMOVED = "The card has been removed.%n";
    //print->import/export/log
    private static final String FILE_NAME = "File name:";
    private static final String FILE_NOT_FOUND = "File not found.%n";
    public static final String THE_LOG_HAS_BEEN_SAVED = "The log has been saved.%n";
    private static final String CARDS_HAVE_BEEN_LOADED = "%d cards have been loaded.%n";
    private static final String CARDS_HAVE_BEEN_SAVED = "%d cards have been saved.%n";
    //print->ask
    private static final String HOW_MANY_TIMES_TO_ASK = "How many times to ask?";
    private static final String PRINT_THE_DEFINITION_OF_CARD = "Print the definition of \"%s\":";
    private static final String CORRECT_ANSWER = "Correct answer.";
    private static final String WRONG_ANSWER_THE_CORRECT_ONE_IS_THE_DEFINITION = "Wrong answer. The correct one is \"%s\", you've just written the definition of \"%s\".";
    private static final String WRONG_ANSWER_THE_CORRECT_ONE_IS = "Wrong answer. The correct one is \"%s\"%n";
    //print->reset stats
    private static final String CARD_STAT_HAS_BEEN_RESET = "Card statistics has been reset.%n";

    //print->exit
    //standard bye bye massage - replaced for ascii art
    //private static final String BYE_BYE = "Bye bye! Great job See you next time";
    private static final String ASCII_ART_BYE_BYE= """
                     __      __
                    ( _\\    /_ )
                     \\ _\\  /_ /\s
                      \\ _\\/_ /_ _
                      |_____/_/ /|
                      (  (_)__)J-)
                      (  /`.,   /
                       \\/  ;   /
                        | === |""";
    //print->hardest card
    private static final String THERE_ARE_NO_CARDS_WITH_ERRORS = "There are no cards with errors.%n";


    private static void selectOperation(String operationType) {
        switch (operationType) {
            case "add":
                addCardToList();
                break;
            case "remove":
                removeCard();
                break;
            case "import":
                runImport();
                break;
            case "export":
                runExport();
                break;
            case "ask":
                runQuiz();
                break;
            case "exit":
                runExit();
                break;
            case "log":
                runExportLog();
                break;
            case "hardest card":
                runGetHardestCard();
                break;
            case "reset stats":
                runResetStats();
                break;
            default:
                break;
        }
    }


    private static void runExportLog() {
        String fileName = inputCardName(FILE_NAME);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String x : LOG_LIST) {
                fileWriter.append(x);
                fileWriter.append("\n");
            }
            fileWriter.append(THE_LOG_HAS_BEEN_SAVED);
            printFormatMessage(THE_LOG_HAS_BEEN_SAVED);
        } catch (IOException ex) {
            ex.getCause();
        }
    }

    public static void main(String[] args) {
        beforeAll(args);
        startMenu();
        afterAll(args);
    }

    private static void afterAll(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-export")) {
                exportFile(args[i + 1]);
            }
        }
    }

    private static void beforeAll(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-import")) {
                importFile(args[i + 1]);
            }
        }
    }


    private static void startMenu() {
        String operationType;
        do {
            printFormatMessage(MAIN_MENU_MESSAGE);
            operationType = getUsersText();
            selectOperation(operationType);
        } while (!operationType.equalsIgnoreCase("exit"));
    }

    private static String getUsersText() {
        String text = scanner.nextLine();
        LOG_LIST.add(text);
        return text;
    }


    private static void addCardToList() {
        String cardName = inputCardName(ASK_CARD_TERM);
        if (isNotContainsQuestByName(cardName)) {
            printFormatMessage(ASK_CARD_DEFINITION);
            String cardDefinition = getUsersText();
            if (isNotContainsQuestByDefinition(cardDefinition)) {
                addCardToList(cardName, cardDefinition);
                printFormatMessage(CARD_HAS_BEEN_ADDED, cardName, cardDefinition);
            } else {
                printFormatMessage(DEFINITION_ALREADY_EXISTS, cardDefinition);
            }
        } else {
            printFormatMessage(THE_CARD_ALREADY_EXISTS, cardName);
        }
    }

    private static void removeCard() {
        String cardName = inputCardName(ASK_CARD_TERM);
        if (isNotContainsQuestByName(cardName)) {
            printFormatMessage(CANT_REMOVE_THIS_CARD, cardName);
        } else {
            removeCardFromList(cardName);
            printFormatMessage(CARD_HAS_BEEN_REMOVED);
        }

    }


    private static void runImport() {
        String fileName = inputCardName(FILE_NAME);
        importFile(fileName);
    }

    private static void importFile(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            final Integer[] counter = {0};
            stream.map(x -> x.split(":")).forEach(y -> {
                if (isNotContainsQuestByName(y[0])) {
                    CARD_LIST.add(new Card(y[0], y[1], Integer.parseInt(y[2])));
                } else {
                    CARD_LIST.stream().filter(x -> x.getCardName().equalsIgnoreCase(y[0])).forEach(x -> {
                        x.setCardDefinition(y[1]);
                        x.setErrorCounter(Integer.parseInt(y[2]));
                    });
                }
                counter[0]++;
            });
            printFormatMessage(CARDS_HAVE_BEEN_LOADED, counter[0]);
        } catch (IOException ex) {
            printFormatMessage(FILE_NOT_FOUND);
        }
    }

    private static void runExport() {
        String fileName = inputCardName(FILE_NAME);
        exportFile(fileName);
    }

    private static void exportFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Card x : CARD_LIST) {
                fileWriter.append(x.toString());
            }
            printFormatMessage(CARDS_HAVE_BEEN_SAVED, CARD_LIST.size());
        } catch (IOException ex) {
            ex.getCause();
        }
    }

    private static void runQuiz() {
        printFormatMessage(HOW_MANY_TIMES_TO_ASK);
        int play_counter = Integer.parseInt(getUsersText());
        Collections.shuffle(CARD_LIST);

        for (int i = 0; i < play_counter; i++) {
            Card card = CARD_LIST.get(random.nextInt(CARD_LIST.size()));
            printFormatMessage(PRINT_THE_DEFINITION_OF_CARD, card.getCardName());
            String answer = getUsersText();
            if (answer.equalsIgnoreCase(card.getCardDefinition())) {
                printFormatMessage(CORRECT_ANSWER);
            } else {
                Optional<Card> searchDef = CARD_LIST.stream().filter(x -> x.getCardDefinition().equalsIgnoreCase(answer)).findFirst();
                if (searchDef.isPresent()) {
                    printFormatMessage(WRONG_ANSWER_THE_CORRECT_ONE_IS_THE_DEFINITION, card.getCardDefinition(), searchDef.get().getCardName());
                } else {
                    printFormatMessage(WRONG_ANSWER_THE_CORRECT_ONE_IS, card.getCardDefinition());
                }
                card.incrementErrorCounter();
            }
        }

    }


    private static void runExit() {
        printFormatMessage(ASCII_ART_BYE_BYE);
    }


    private static void runResetStats() {
        CARD_LIST.forEach(Card::clearErrorCounter);
        printFormatMessage(CARD_STAT_HAS_BEEN_RESET);
    }


    private static void runGetHardestCard() {
        Optional<Card> card = CARD_LIST.stream().filter(x -> x.getErrorCounter() > 0).max(Comparator.comparing(Card::getErrorCounter));
        if (card.isPresent()) {
            List<Card> cardList = CARD_LIST.stream().filter(cards -> cards.getErrorCounter().equals(card.get().getErrorCounter())).collect(Collectors.toList());
            if (cardList.size() == 1) {
                printFormatMessage("The hardest card is \"%s\". You have %d errors answering it.%n",
                        cardList.get(0).getCardName(),
                        cardList.get(0).getErrorCounter());
            } else {
                StringBuilder sb = new StringBuilder();
                cardList.forEach(c -> sb.append("\"").append(c.getCardName()).append("\", "));
                printFormatMessage("The hardest card is %s. You have %d errors answering it.%n",
                        sb.substring(0, sb.toString().length() - 2),
                        cardList.get(0).getErrorCounter());

            }

        } else {
            printFormatMessage(THERE_ARE_NO_CARDS_WITH_ERRORS);
        }
    }

    private static void printFormatMessage(String messageBody, Object... textValue) {
        System.out.printf(messageBody, textValue);
        LOG_LIST.add(String.format(messageBody, textValue));
        System.out.println();
    }


    private static void removeCardFromList(String cardName) {
        CARD_LIST.removeIf(card -> card.getCardName().equalsIgnoreCase(cardName));
    }

    private static String inputCardName(String text) {
        printFormatMessage(text);
        return getUsersText();
    }


    private static void addCardToList(String cardName, String cardDefinition) {
        CARD_LIST.add(new Card(cardName, cardDefinition));
    }

    private static boolean isNotContainsQuestByName(String cardName) {
        return CARD_LIST.stream().noneMatch(card -> card.getCardName().equalsIgnoreCase(cardName));
    }

    private static boolean isNotContainsQuestByDefinition(String cardDefinition) {
        return CARD_LIST.stream().noneMatch(card -> card.getCardDefinition().equalsIgnoreCase(cardDefinition));
    }
}
