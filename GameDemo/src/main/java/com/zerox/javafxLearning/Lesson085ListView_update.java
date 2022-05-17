package com.zerox.javafxLearning;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * @Author: zhuxi
 * @Time: 2022/5/17 14:41
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson085ListView_update extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView<String> listView = new ListView<>();

        ObservableList<String> obsList = listView.getItems();
        obsList.add("data - a");
        obsList.add("data - b");
        obsList.add("data - c");
        obsList.add("data - d");
        obsList.add("data - e");

        obsList.addListener((ListChangeListener.Change<? extends String> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("添加动作");
                }
                if (c.wasRemoved()) {
                    System.out.println("删除动作");
                }
                if (c.wasReplaced()) {
                    System.out.println("替换动作");
                }
                if (c.wasUpdated()) {
                    System.out.println("更新动作");
                }
                if (c.wasPermutated()) {
                    System.out.println("排序动作");
                }
            }
        });

        listView.setPrefWidth(100);
        listView.setPrefHeight(300);
        Button bu = new Button("button");

        // ============================================ Property ===============================================

        SimpleStringProperty s1 = new SimpleStringProperty("A");
        SimpleStringProperty s2 = new SimpleStringProperty("B");
        SimpleStringProperty s3 = new SimpleStringProperty("C");
        SimpleStringProperty s4 = new SimpleStringProperty("D");

        // 实现 callback，使得界面在 Property 更新时也更新
        ObservableList<SimpleStringProperty> list = FXCollections.observableArrayList(param -> new SimpleStringProperty[]{param});

        ListView<SimpleStringProperty> listView2 = new ListView<>(list);

        ObservableList<SimpleStringProperty> obsList2 = listView2.getItems();
        obsList2.add(s1);
        obsList2.add(s2);
        obsList2.add(s3);
        obsList2.add(s4);

        listView2.setCellFactory(TextFieldListCell.forListView(new StringConverter<SimpleStringProperty>() {
            @Override
            public String toString(SimpleStringProperty object) {
                return object.get();
            }

            @Override
            public SimpleStringProperty fromString(String string) {
                return null;
            }
        }));

        obsList2.addListener((Observable o) -> {
            ObservableList<SimpleStringProperty> data = (ObservableList) o;
            data.forEach(t -> System.out.println(t.get()));
        });

        obsList2.addListener((ListChangeListener.Change<? extends SimpleStringProperty> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("添加动作");
                }
                if (c.wasRemoved()) {
                    System.out.println("删除动作");
                }
                if (c.wasReplaced()) {
                    System.out.println("替换动作");
                }
                if (c.wasUpdated()) {
                    System.out.println("更新动作");
                }
                if (c.wasPermutated()) {
                    System.out.println("排序动作");
                }
            }
        });

        listView2.setPrefWidth(100);
        listView2.setPrefHeight(300);
        Button bu2 = new Button("button2");

        // ============================================ Bean ===============================================

        Data d1 = new Data("A", "10");
        Data d2 = new Data("B", "20");
        Data d3 = new Data("C", "30");
        Data d4 = new Data("D", "40");

        ListView<Data> listView3 = new ListView<>();

        ObservableList<Data> obsList3 = listView3.getItems();
        obsList3.add(d1);
        obsList3.add(d2);
        obsList3.add(d3);
        obsList3.add(d4);

        listView3.setCellFactory(TextFieldListCell.forListView(new StringConverter<Data>() {
            @Override
            public String toString(Data object) {
                return object.getName() + " - " + object.getAge();
            }

            @Override
            public Data fromString(String string) {
                return null;
            }
        }));

        obsList3.addListener((Observable o) -> {
            ObservableList<Data> data = (ObservableList) o;
            data.forEach(t -> System.out.println(t.getName() + " - " + t.getAge()));
        });

        obsList3.addListener((ListChangeListener.Change<? extends Data> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("添加动作");
                }
                if (c.wasRemoved()) {
                    System.out.println("删除动作");
                }
                if (c.wasReplaced()) {
                    System.out.println("替换动作");
                }
                if (c.wasUpdated()) {
                    System.out.println("更新动作");
                }
                if (c.wasPermutated()) {
                    System.out.println("排序动作");
                }
            }
        });

        listView3.setPrefWidth(100);
        listView3.setPrefHeight(300);
        Button bu3 = new Button("button3");

        // ============================================ Bean with Property ===============================================

        DataPro dp1 = new DataPro("A", "10");
        DataPro dp2 = new DataPro("B", "20");
        DataPro dp3 = new DataPro("C", "30");
        DataPro dp4 = new DataPro("D", "40");

        ObservableList<DataPro> list4 = FXCollections.observableArrayList(param ->
                new SimpleStringProperty[]{param.ageProperty(), param.nameProperty()});

        ListView<DataPro> listView4 = new ListView<>(list4);

        ObservableList<DataPro> obsList4 = listView4.getItems();
        obsList4.add(dp1);
        obsList4.add(dp2);
        obsList4.add(dp3);
        obsList4.add(dp4);

        listView4.setCellFactory(TextFieldListCell.forListView(new StringConverter<DataPro>() {
            @Override
            public String toString(DataPro object) {
                return object.getName() + " - " + object.getAge();
            }

            @Override
            public DataPro fromString(String string) {
                return null;
            }
        }));

        obsList4.addListener((Observable o) -> {
            ObservableList<DataPro> data = (ObservableList) o;
            data.forEach(t -> System.out.println(t.getName() + " - " + t.getAge()));
        });

        obsList4.addListener((ListChangeListener.Change<? extends DataPro> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("添加动作");
                }
                if (c.wasRemoved()) {
                    System.out.println("删除动作");
                }
                if (c.wasReplaced()) {
                    System.out.println("替换动作");
                }
                if (c.wasUpdated()) {
                    System.out.println("更新动作");
                }
                if (c.wasPermutated()) {
                    System.out.println("排序动作");
                }
            }
        });

        listView4.setPrefWidth(100);
        listView4.setPrefHeight(300);
        Button bu4 = new Button("button4");

        // ===================================== Bean with Property and String ========================================

        DataPro2 dp2_1 = new DataPro2("A", "10");
        DataPro2 dp2_2 = new DataPro2("B", "20");
        DataPro2 dp2_3 = new DataPro2("C", "30");
        DataPro2 dp2_4 = new DataPro2("D", "40");

        ObservableList<DataPro2> list5 = FXCollections.observableArrayList(param ->
                new SimpleStringProperty[]{param.ageProperty()});

        ListView<DataPro2> listView5 = new ListView<>(list5);

        ObservableList<DataPro2> obsList5 = listView5.getItems();
        obsList5.add(dp2_1);
        obsList5.add(dp2_2);
        obsList5.add(dp2_3);
        obsList5.add(dp2_4);

        listView5.setCellFactory(TextFieldListCell.forListView(new StringConverter<DataPro2>() {
            @Override
            public String toString(DataPro2 object) {
                return object.getName() + " - " + object.getAge();
            }

            @Override
            public DataPro2 fromString(String string) {
                return null;
            }
        }));

        obsList5.addListener((Observable o) -> {
            ObservableList<DataPro2> data = (ObservableList) o;
            data.forEach(t -> System.out.println(t.getName() + " - " + t.getAge()));
        });

        obsList5.addListener((ListChangeListener.Change<? extends DataPro2> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("添加动作");
                }
                if (c.wasRemoved()) {
                    System.out.println("删除动作");
                }
                if (c.wasReplaced()) {
                    System.out.println("替换动作");
                }
                if (c.wasUpdated()) {
                    System.out.println("更新动作");
                }
                if (c.wasPermutated()) {
                    System.out.println("排序动作");
                }
            }
        });

        listView5.setPrefWidth(100);
        listView5.setPrefHeight(300);
        Button bu5 = new Button("button5");

        AnchorPane an = new AnchorPane();

        an.getChildren().addAll(listView, listView2, listView3, listView4, listView5, bu, bu2, bu3, bu4, bu5);

        AnchorPane.setTopAnchor(listView, 100.0);
        AnchorPane.setLeftAnchor(listView, 50.0);
        AnchorPane.setTopAnchor(listView2, 100.0);
        AnchorPane.setLeftAnchor(listView2, 200.0);
        AnchorPane.setTopAnchor(listView3, 100.0);
        AnchorPane.setLeftAnchor(listView3, 350.0);
        AnchorPane.setTopAnchor(listView4, 100.0);
        AnchorPane.setLeftAnchor(listView4, 500.0);
        AnchorPane.setTopAnchor(listView5, 100.0);
        AnchorPane.setLeftAnchor(listView5, 650.0);

        AnchorPane.setTopAnchor(bu, 50.0);
        AnchorPane.setLeftAnchor(bu, 50.0);
        AnchorPane.setTopAnchor(bu2, 50.0);
        AnchorPane.setLeftAnchor(bu2, 200.0);
        AnchorPane.setTopAnchor(bu3, 50.0);
        AnchorPane.setLeftAnchor(bu3, 350.0);
        AnchorPane.setTopAnchor(bu4, 50.0);
        AnchorPane.setLeftAnchor(bu4, 500.0);
        AnchorPane.setTopAnchor(bu5, 50.0);
        AnchorPane.setLeftAnchor(bu5, 650.0);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Java fx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.show();

        bu.setOnAction(event -> {
            // 该 set 方法是由添加动作和删除动作组成的，所以 wasAdded() 和 wasRemoved() 也是 true
            obsList.set(0, "button set");
        });

        bu2.setOnAction(event -> {
            s1.set("change property");
            // 不使用 FXCollections.observableArrayList 的话，只写上面这一行修改 SimpleStringProperty 内部的文字，界面不会改变。必须刷新
            // 使用 FXCollections.observableArrayList 后就不需要，这种情况下，会出现“更新动作”（即 wasUpdated() == true）
//            listView2.refresh();
        });

        bu3.setOnAction(event -> {
            d1.setAge("100");
            // 没办法 callback，只能更新。这种情况下不会触发监听器
            listView3.refresh();
        });

        bu4.setOnAction(event -> {
            dp1.setAge("100");
        });

        bu5.setOnAction(event -> {
            // 先修改非 Property 的对象，然后修改 Property 对象，这样两个都会被刷新
            dp2_1.setName("new name");
            dp2_1.setAge("100");
        });
    }

    class Data {
        private String name;
        private String age;

        public Data(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    class DataPro {
        private SimpleStringProperty name = new SimpleStringProperty();
        private SimpleStringProperty age = new SimpleStringProperty();

        public DataPro(String name, String age) {
            this.name.set(name);
            this.age.set(age);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getAge() {
            return age.get();
        }

        public SimpleStringProperty ageProperty() {
            return age;
        }

        public void setAge(String age) {
            this.age.set(age);
        }
    }

    class DataPro2 {
        private String name;
        private SimpleStringProperty age = new SimpleStringProperty();

        public DataPro2(String name, String age) {
            this.name = name;
            this.age.set(age);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age.get();
        }

        public SimpleStringProperty ageProperty() {
            return age;
        }

        public void setAge(String age) {
            this.age.set(age);
        }
    }
}
