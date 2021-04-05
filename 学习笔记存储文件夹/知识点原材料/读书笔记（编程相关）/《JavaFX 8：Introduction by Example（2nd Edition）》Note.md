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

