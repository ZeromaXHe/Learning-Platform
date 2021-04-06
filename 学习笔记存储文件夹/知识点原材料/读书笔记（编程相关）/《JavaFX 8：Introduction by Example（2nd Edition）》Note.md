# Introduction

What is JavaFX?
什么是JavaFX？

JavaFX is Java's next-generation graphical user interface(GUI) toolkit that allows developers to rapidly build rich cross-platform application. Built from the ground up, JavaFX takes advantage of modern GPUs through hardware-accelerated graphics, animation, and UI controls. The new JavaFX 8 is a pure Java language application programming interface(API). The goal of JavaFX is to be used across many type of devices, such as embedded devices, smartphones, TVs, tablet computers, and desktops.
JavaFX是可以让开发者快速构建跨平台应用的Java新一代GUI工具包。从基础开始构建，JavaFX利用了现代GPU的硬件加速图像、动画和UI控制。新的JavaFX 8 是纯Java 语言API。JavaFX的目标是在各种类型的设备上使用，例如嵌入式设备、智能手机、电视、平板电脑与桌面。

Before the creation of JavaFX, the development of rich client-side applications involved the gathering of many separate libraries and APIs to achieve highly functional applications. These separate libraries include media, UI controls, Web, 3D, and 2D APIs. Because integrating these APIs together can be rather difficult, the talented engineers at Oracle created a new set of JavaFX libraries that roll up all the same capabilities under one roof. JavaFX is the Swiss Army Knife of GUI toolkits. JavaFX 8 is a pure Java (language) API that allows developers to leverage existing Java libraries and tools.  
在JavaFx 出现之前，多客户端应用开发者需要很多不同的库和API来实现高功能的应用。这些不同的库包括视频，UI控制，网络，3D和2D API。因为集成这些API是非常困难的，Oracle的天才工程师创建了一套新的JavaFX 库来将所有相同能力的工具归纳于一个屋檐下。JavaFX是GUI工具包中的瑞士军刀。JavaFX 8 是让开发者可以整合已有Java库和工具的纯Java API。

> leverage 英[ˈliːvərɪdʒ]  美[ˈlevərɪdʒ]
> n.  影响力; 杠杆作用; 杠杆效力;
> v.  举债经营; 借贷收购;

## Some History

In 2005 Sun Microsystems acquired the company SeeBeyond, where a software engineer named Chris Oliver created a graphics-rich scripting language known as F3 (Form Follows Function). F3 was later unveiled by Sun Microsystems at the 2007 JavaOne conference as JavaFX. On April 20, 2009, Oracle Corporation announced the acquisition of Sun Microsystems, making Oracle the new steward of JavaFX.  

At JavaOne 2010, Oracle announced the JavaFX roadmap, which included its plans to phase out the JavaFX scripting language and re-create the JavaFX platform for the Java platform as Java-based APIs. As promised based on the 2010 roadmap, JavaFX 2.0 SDK was released at JavaOne, in October 2011. In addition to the release of JavaFX 2.0, Oracle announced its commitment to take steps to open-source JavaFX, thus allowing the community to help move the platform forward. Open sourcing JavaFX will increase its adoption, enable a quicker turnaround time on bug fixes, and generate new enhancements.  

Between JavaFX 2.1 and 2.2 the number of new features grew rapidly. Please refer to Table FM-1 for the numerous features included between versions 2.1 and 2.2. JavaFX 2.1 was the official release of the Java SDK on a MacOS. JavaFX 2.2 was the official release of the Java SDK on a Linux operating system.  

The new Java 8 release was announced March 18, 2014. Java 8 has many new APIs and language enhancements, which includes lambdas, Stream API, Nashorn JavaScript engine, and JavaFX APIs. Relating to JavaFX 8, the following are such as: 3D graphics, Rich text support, and Printing APIs.  

# Chapter 1: Getting Started

## Creating a JavaFX Hello World Application

### Using the Command-Line Prompt

The second method of developing JavaFX 8 applications is to use a common text editor and the command line prompt (terminal). By learning how to compile and execute applications on the command-line prompt you will learn about the classpath and where the executable files reside. This exposure should sharpen your skills when you are in environments where fancy IDEs and/or editors aren’t easily available.

Working from the command line you will basically use popular text editors such as vi, Emacs, or Notepad to code your JavaFX Hello World application. An example is shown in Listing 1-1.  

~~~java
package helloworldmain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A JavaFX Hello World
 * @author carldea
 */
public class HelloWorldMain extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Hello World");
    	Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	System.out.println("Hello World");
            }
		});
		root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
~~~

Once the Java file is created you will use the command-line prompt or terminal window to compile and run your JavaFX application. Following are the steps to create a JavaFX Hello World application to be compiled and run on the command-line prompt.  

1. Copy and paste the code from Listing 1-1 into a text editor, and save the file as `HelloWorldMain.java`.
2. After saving the file named HelloWorldMain.java, on the command-line prompt you will navigate to the directory location of the file. The examples to follow assume you have saved the file to a directory named `myprojects/helloworld`. Change directory as follows on Mac OS X, Linux, and Unix-based operating systems:
   `cd ~/myprojects/helloworld`
3. Change directory on Windows operating systems using the following command:
   `C:\Users\myusername>cd myprojects\helloworld`
   `C:\Users\myusername\myprojects\helloworld>`
4. Compile the source code file HelloWorldMain.java using the Java compiler javac with the -d switch with the current path. Type the following command:
   `C:\Users\myusername\myprojects\helloworld>javac -d . HelloWorldMain.java  `

Notice the `-d .` before the filename. The dot denotes the current directory. The –d option (destination directory) lets the Java compiler know where to put compiled class files based on their package namespace. In this scenario, the HelloWorldMain package statement (namespace) is helloworldmain, which will create a subdirectory under the current directory assuming we are in the myprojects/helloworld directory.  

When finished compiling, your directory structure should resemble the following:

~~~
|myprojects
    |helloworld
        |HelloWorldMain.java
        |helloworldmain
        	|HelloWorldMain.class
~~~

5. Run and test your JavaFX Hello World application. Assuming you are located in the same directory as the HelloWorldMain.java file, type the following command to run your JavaFX Hello World application from the command-line prompt:
   `C:\Users\myusername\myprojects\helloworld>java helloworldmain.HelloWorldMain  `

## Walking Through the Code

You’ll notice in the source code that JavaFX-based applications extend (inherit) from the javafx.application.Application class. The Application class provides application life cycle functions such as initializing, launching, starting, and stopping during runtime. This provides a mechanism for Java applications to launch JavaFX GUI components separate from the main thread. The code in Listing 1-2 is a skeleton of the JavaFX Hello World application, having a main() method and an overridden start() method.  

~~~java
public class HelloWorldMain extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Main thread
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // JavaFX application thread
        // JavaFX code here...
    }
}
~~~

Here, in our main() method’s entry point we launch the JavaFX application by simply passing in the command-line arguments to the `Application.launch()` method. To access any arguments passed into the launch() method you can invoke the `getParameters()` method of the Application class. Please see the Javadoc documentation for details on various ways to access named and raw arguments. After the `Application.launch()` method has executed, the application will enter a ready state, and the framework internals will invoke the start() method to begin. At this point, the program execution occurs on the JavaFX application thread and not on the main thread. When the start() method is invoked, a JavaFX `javafx.stage.Stage` object is available for the developer to use and manipulate. Following is the overridden Application start() method:  

~~~java
@Override
public void start(Stage primaryStage) {...}
~~~

When the program begins in the start() method, a separate thread of execution occurs, called the JavaFX application thread. Keep in mind that running on the JavaFX application thread is synonymous with running on Java Swing’s event dispatch thread. Later in this book, you will learn how to create background processes to avoid blocking the JavaFX application thread. When you know how to build applications to avoid blocking the GUI, the user will notice that your application is much more responsive (snappy) under heavy usage. Mastering responsiveness in GUI applications is an important concept in enhancing usability and the overall user experience.  

### JavaFX Scene Graph

You’ll notice that some objects are oddly named, such as Stage and Scene. The designers of the API have modeled things on the idea of a theater or a play in which actors perform in front of an audience. In this analogy, a play consists of one-to-many scenes that actors perform in. And, of course, all scenes are performed on a stage. In JavaFX the Stage is equivalent to an application window similar to Java Swing API JFrame or JDialog on the desktop. Depending on the device, such as a Raspberry Pi (Raspbian), there may be only one stage. You can think of a Scene object as a content pane, similar to Java Swing’s JPanel, capable of holding zero-to-many Node objects (children)  

Proceeding with our example, in the start() method we see that for a JavaFX desktop window (stage) you can set the title bar using the setTitle() method. Next, you create a root node (Group), which is added to the Scene object as the top-level surface for the application window. The following code snippet shows how to set the title and create the scene:  

> snippet	英[ˈsnɪpɪt] 美[ˈsnɪpɪt]
> n.	一小条(消息); 一则(新闻); 一小段(谈话、音乐等);

~~~java
primaryStage.setTitle("Hello World");
Group root = new Group();
Scene scene = new Scene(root, 300, 250);
~~~

### JavaFX Node

A JavaFX Node is a fundamental base class for all scene graph nodes to be rendered. The following graphics capabilities can be applied to Nodes: scaling, transforms, translations, and effects.

Some of the most commonly used nodes are UI controls and Shape objects. Similar to a tree data structure, a scene graph will contain children nodes by using a container class such as the Group or Pane class. We’ll learn more about the Group class later when we look at the ObservableList class, but for now you can think of them as Java List or Collection classes that are capable of holding child Node objects. In the following code a button (Button) node is created to be positioned on the scene and set with an event handler (EventHandler<ActionEvent>) that responds when the user presses the button. The handler code will output the word “Hello World” on the console.  

~~~java
Button btn = new Button();
btn.setLayoutX(100);
btn.setLayoutY(80);
btn.setText("Hello World");
btn.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) {
    	System.out.println("Hello World");
    }
});
root.getChildren().add(btn);
~~~

Once the child nodes have been added to our root Group via the getChildren().add() method, we set the primaryStage’s scene and call the show() method on the Stage object to display a JavaFX application window. By default the window will allow a user to minimize, maximize, and close (exit) the application. Following is the code to set the scene and display (show) the JavaFX application window (Stage):  

~~~java
primaryStage.setScene(scene);
primaryStage.show();
~~~

### Packaging a JavaFX Application

At some point you will want to distribute or deploy your JavaFX application. To handle the numerous application packaging and deployment strategies Oracle’s JavaFX team has created a JavaFX Packager tool to assist developers to build, package, and deploy their applications.   

To give you a taste of the JavaFX Packager tool’s capabilities, I will show you how to package the HelloWorldMain classes into a single executable jar file that a user can double-click or launch from a command prompt (terminal).

~~~sh
cd myprojects/helloworld

javac -d . HelloWorldMain.java

javafxpackager -createjar -appclass helloworldmain.HelloWorldMain -srcdir . -outdir out -outfile helloworld.jar -v  
~~~

Table 1-1 describes the common options and switches that are used in building a JavaFX jar executable application.  

| Option/Switch | Example                       | Description                                                  |
| ------------- | ----------------------------- | ------------------------------------------------------------ |
| -createjar    | --                            | Creates a JavaFX JAR executable application                  |
| -appclass     | helloworldmain.HelloWorldMain | Specifies the fully qualified name of the class containing the main() method. |
| -srcdir       | .                             | The top-level location of the parent directory holding the compiled classes (current directory). |
| -outdir       | out                           | The destination where the packaged jar file will be created  |
| -outfile      | helloworld.jar                | Specifies the name of the executable jar file.               |
| -v            | --                            | Allows verbose displays logging information when executing javafxpackager |

> verbose	英[vɜːˈbəʊs] 美[vɜːrˈboʊs]
> adj.	冗长的; 啰唆的; 唠叨的;

To run the jar executable on the command line, you simply type the following and press Enter:

~~~sh
javaw -jar out/helloworld.jar  
~~~

# Chapter 2: JavaFX Fundamentals

There is nothing more frustrating than receiving complicated advice as a solution to a problem. Because of this, I have always made it a point to focus on the fundamentals. In order to render graphics on the JavaFX scene, you will need basic shapes and colors. Expanding upon your knowledge of the JavaFX scene graph, which you visited toward the end of Chapter 1, you will learn in this chapter how to draw and manipulate 2D shapes on the JavaFX scene graph.

Even more fundamental than a shape object is JavaFX’s `javafx.scene.Node` class. The Node class is a fundamental base class for all JavaFX scene graph nodes. The Node class provides the ability to transform, translate, and apply effects to any node. Many examples in this chapter involve the `javafx.scene.shape.Shape` class, which is a descendant of the Node class. Therefore, the Shape class will inherit all of the Node class’ capabilities. In this chapter you will explore many derived classes that inherit from Shape. You will begin this chapter by examining JavaFX’s most basic shape, the Line node.

## JavaFX Lines

Rendering lines in JavaFX is conceptually similar to Euclidean geometry in that two (x, y) coordinate points connect to draw a segment into space. When drawn on the JavaFX scene graph, lines are rendered using the screen coordinate space (system). In geometry, a line is defined as a segment connected by two points in space, although it lacks a width (thickness) and color value. Does this mean that the line is invisible? In the computer world, physical devices plot pixels and shapes that occupy real surfaces. Monitors and printers are examples of such surfaces. Because of this method of production, modern graphics programming has adopted the standard screen coordinate system. In this system, lines that are drawn are visible and have attributes of width and color.

> Euclidean	
> adj. 欧氏; 欧几里得; 欧几里德; 欧几里得的; 欧几里德的;

In a nutshell, the standard screen coordinate system places (0, 0) at the upper-left corner; this differs from the Cartesian coordinate system, where (0, 0) is placed at the center of the graphing area or the point at which the x and y axes converge. In both coordinate systems, the x coordinate has the same effect when moving a point along the x axis. However, when using the screen coordinate system to move along the y axis, the effect is opposite that of the Cartesian system. In other words, the y coordinate’s value increases when moving a point in a downward direction (top to bottom). 

> Cartesian 英[kɑːˈtiːziən] 美[kɑːrˈtiːziən]
> adj.	笛卡尔坐标系; 笛卡尔; 直角; 笛卡尔的; 笛卡儿积;
>
> converge	英[kənˈvɜːdʒ] 美[kənˈvɜːrdʒ]
> v.	汇集; 聚集; 集中; (向某一点)相交，会合; (思想、政策、目标等)十分相似，相同;

As you learn more about JavaFX, you will discover many scene graph objects, such as lines, circles, and rectangles. These objects are derived classes of the Shape class. All shape objects can perform geometric operations between two shaped areas, such as subtract, intersect and union. By mastering the Shape API, you will begin to see endless possibilities. Now let’s discuss how to create lines.

To draw lines in JavaFX, you will use the javafx.scene.shape.Line class. When creating a Line instance you need to specify a start (x, y) coordinate and an end coordinate to draw a line. There are two ways to specify the start and end points when creating Line nodes. The first method is to use a constructor with the parameters startX, startY, endX, and endY. The data types of all the parameters are double values, which provide floating-point decimal precision. The following line has a start point (100, 10) and end point (10, 110) created using a constructor.

~~~java
Line line = new Line(100, 10, 10, 110);
~~~

The second method to create a line node is to instantiate a Line class by using the empty default constructor and subsequently setting each attribute using associated setter methods. The following code snippet shows how to specify a line’s start and end points using setter methods.

~~~java
Line line = new Line();
line.setStartX(100);
line.setStartY(10);
line.setEndX(10);
line.setEndY(110);
~~~

Line nodes drawn on the scene graph default to a stroke width of 1.0 (double) and a stroke color of black (Color.BLACK). According to the Javadoc, all shapes have a stroke color of null (no color) except Line, Polyline, and Path nodes.

> stroke color 英[strəʊk ˈkʌlə(r)] 美[stroʊk ˈkʌlər] 
> n. 笔画颜色; 描边颜色; 笔触颜色; 画笔颜色;

Now that you know how to draw lines on the scene graph, you are probably wondering how to be more creative with lines. Creating different kinds of lines is simple; you basically set properties inherited from the parent class (javafx.scene.shape.Shape). Table 2-1 shows the properties you can set on a line (Shape). To retrieve or modify each property, you will use its appropriate getter and setter methods. The table lists each property name and its data type, with a description. Please refer to the Javadoc documentation for more details.

| Property         | Data Type                         | Description                                                  |
| ---------------- | --------------------------------- | ------------------------------------------------------------ |
| fill             | javafx.scene.paint.Paint          | A color to fill inside a shape.                              |
| smooth           | Boolean                           | True turns on anti-aliasing, otherwise false.                |
| strokeDashOffset | Double                            | The offset (distance) into the dashed pattern.               |
| strokeLineCap    | javafx.scene.shape.StrokeLineCap  | The cap style on the end of a line or path. There are three styles: StrokeLineCap.BUTT, StrokeLineCap.ROUND, and StrokeLineCap.SQUARE. |
| StrokeLineJoin   | javafx.scene.shape.StrokeLineJoin | Decoration when lines meet. There are three types: StrokeLineJoin.MITER, StrokeLineJoin.BEVEL, and StrokeLineJoin.ROUND. |
| StrokeMiterLimit | Double                            | The limit of a miter joint. Used along with the miter join decoration(StrokeLineJoin.MITER) |
| stroke           | javafx.scene.paint.Paint          | A color for the shape's line stroke                          |
| strokeType       | javafx.scene.shape.StrokeType     | Where to draw the stroke around the boundary of a Shape node. There are three types: StrokeType.CENTERED, StrokeType.INSIDE, and StrokeType.OUTSIDE. |
| strokeWidth      | Double                            | A stroke line's width.                                       |

> miter	英[ˈmaɪtə(r)] 美[ˈmaɪtər]
> n.	主教法冠；斜接;
> v.	给主教等加冠；使斜接;
>
> bevel	英[ˈbevl] 美[ˈbevl]
> n.	斜边; 斜面; 斜角规;
> v.	把（物体的方形边）改成斜面边;

## Drawing Lines

To get a better idea of how you would use a shape’s properties based on Table 2-1, let’s look at an example. Figure 2-2 is the output of Listing 2-1, the DrawingLines.java source code, demonstrating the drawing of JavaFX lines. The JavaFX application in the listing draws three lines with various properties modified. Some common properties used in this example are stroke dash offset, stroke line cap, stroke width, and stroke color.

In Figure 2-2 you can see that the first line (top) is a thick red line with a dashed stroke pattern. The second line is a thick white line having rounded end caps (line cap). Last is an ordinary blue line having the same thickness as the others. You will also notice in Figure 2-2 underneath the blue line are a label and slider control; these allow the user to change the red (top) line’s stroke dash offset property dynamically. The label control displays the dash offset value as the slider control moves.

Listing 2-1shows the source code for DrawingLines.java.

~~~java
package jfx8ibe;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Drawing Lines
 * Listing 2-1 DrawingLines.java
 * @author carldea
 */
public class DrawingLines extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 2 Drawing Lines");
        
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150, Color.GRAY);
        
        // Red line
        Line redLine = new Line(10, 10, 200, 10);

        // setting common properties
        redLine.setStroke(Color.RED);
        redLine.setStrokeWidth(10);
        redLine.setStrokeLineCap(StrokeLineCap.BUTT);

        // creating a dashed pattern
        redLine.getStrokeDashArray().addAll(10d, 5d, 15d, 5d, 20d);
        redLine.setStrokeDashOffset(0);

        root.getChildren().add(redLine);

        // White line
        Line whiteLine = new Line(10, 30, 200, 30);
        whiteLine.setStroke(Color.WHITE);
        whiteLine.setStrokeWidth(10);
        whiteLine.setStrokeLineCap(StrokeLineCap.ROUND);

        root.getChildren().add(whiteLine);

        // Blue line
        Line blueLine = new Line(10, 50, 200, 50);
        blueLine.setStroke(Color.BLUE);
        blueLine.setStrokeWidth(10);

        root.getChildren().add(blueLine);


        // slider min, max, and current value
        Slider slider = new Slider(0, 100, 0);
        slider.setLayoutX(10);
        slider.setLayoutY(95);

        // bind the stroke dash offset property
        redLine.strokeDashOffsetProperty()
            .bind(slider.valueProperty());
        root.getChildren()
            .add(slider);

        Text offsetText = new Text("Stroke Dash Offset: 0.0");
        offsetText.setX(10);
        offsetText.setY(80);
        offsetText.setStroke(Color.WHITE);

        // display stroke dash offset value
        slider.valueProperty()
            .addListener((ov, curVal, newVal) ->
                         offsetText.setText("Stroke Dash Offset: " + slider.getValue()));
        root.getChildren().add(offsetText);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
 	 * @param args the command line arguments
 	 */
    public static void main(String[] args) {
        launch(args);
    }
}
~~~

DrawingLines.java begins by setting the title of the Stage window using the setTitle() method. Then it creates a root node (javafx.scene.Group) for the Scene object. All root nodes (those extending javafx.scene.Parent) have a method getChildren().add() to allow any JavaFX node to be added to the scene graph. By default the Scene object will be filled with a white background; however, because one of our lines is white, the code will set the scene to have a color of gray (Color.GRAY). This will allow some contrast in order to see the white line. Next is the code that creates the three lines (red, white and blue).

In the first line, the code sets the common properties of a Line node. The common properties are stroke color, stroke width, and stroke line cap. As noted earlier, lines do not have a shape internally, so a line’s fill color property is set to null (no color) and stroke color defaults to black. To set the stroke color you can use built-in JavaFX colors by using the javafx.scene.paint.Color class. For instance, for the color red you will use Color.RED. There are many ways to specify color, such as using RGB, HSB, or Web hex values. All three methods also have the ability to specify alpha value (transparency). Later, you will see additional methods for coloring shapes. Please refer to the Javadoc to learn more about color (`javafx.scene.paint.Color`). After setting the stroke’s outline color you can set the line’s stroke width (thickness) by using the `setStrokeWidth()` method. Shapes also have a stroke line cap property. This property specifies the style of the ends of the line. For instance, specifying the stroke line cap as butt (`StrokeLineCap.BUTT`) will make a flat square end, while a round (`StrokeLineCap.ROUND`) style will appear with a rounded end. The following code snippet sets a line node’s common shape properties:

~~~java
// setting common properties
redLine.setStroke(Color.RED);
redLine.setStrokeWidth(10);
redLine.setStrokeLineCap(StrokeLineCap.BUTT);
~~~

After setting the common properties on the red Line node, the example code creates a dashed pattern. To form a dashed pattern you would simply add double values to the `getStrokeDashArray().add()` method. Each value represents the number of pixels for a dash segment. To set the dash segment array, the first value (10d) is a visible dash segment 10 pixels wide. Next is a five-pixel empty segment (not visible). Following that is a visible dash segment 15 pixels wide, and so on. Because the array has an odd number of values, you can see that as the pattern repeats itself, the first value (10d) becomes a 10-pixel empty segment (not visible). Following is the code to create a dashed pattern for a Line node:

~~~java
// creating a dashed pattern
redLine.getStrokeDashArray().addAll(10d, 5d, 15d, 5d, 20d);
redLine.setStrokeDashOffset(0);
~~~

By default the dash offset property value is zero. To see what happens when the offset property changes, the user can drag the slider thumb to the right to increase the offset. The stroke dash offset is the distance into the current pattern to begin the line drawing.

Because the other two lines are pretty much the same in the way they modify common properties, I will not explain them any further. I trust you now understand the fundamentals of creating lines. One last thing to mention about this example is the Slider control and the way it is wired up using binding. We will discuss binding further in Chapter 3.

Notice in Listing 2-1 also that after the three lines just discussed, the slider control has handler code that updates a Text node dynamically to display the dash offset value. Also notice the invocation to the `addListener()` method, containing concise code added as a change listener. This may look odd to you; however, it reflects a new Java 8 feature called *lambda expressions*, which you will learn more about in Chapter 3. The following code snippet creates a change listener using lambdas instead of an anonymous inner class (ChangeListener).

~~~java
// display stroke dash offset value
slider.valueProperty()
    .addListener( (ov, oldVal, newVal) ->
                 offsetText.setText("Stroke Dash Offset: " + slider.getValue()));
~~~

Learning about how to draw lines will help you apply the same knowledge to any Shape node in JavaFX. These important concepts will allow you to create any kind of shape styled the way you see fit. Speaking of shapes, that’s what we’ll discuss next.

## Drawing Shapes

