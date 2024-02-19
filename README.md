# orderItemProject_20240206
order item exercise

Q1: Implement the simple RESTful API for managing an order list. The APIs include the following functionality:
 1. Create a new order item.
 2. Retrieve a list of all order items.
 3. Retrieve detailed information about a specific order item.
 4. Update the content of a specific order item.
 5. Update the content of a specific order item name.
 6. Delete a specific order item.

Ans: <br>
 #### 以下解答皆有附上Junit可以進行測試. <br>
 1.<br>
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/9aa6cf86-2552-408f-bdb7-5943f00ee271).
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/50b886e0-d5f3-404b-8852-876387796d58).
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/4a5e9070-39f8-429a-965d-3172b8706d28).
 
 2.<br>
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/0984ce71-8012-4952-bb90-e02f7d524484)
 
 3.<br>
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/de4befda-11e5-4b79-a18d-9cd2a377e62d)
 
 4.<br>
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/9daaa570-15c0-47d4-9f86-ad3990b7bb21)
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/9142d95d-6897-4e8f-9478-814b5884f076)
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/30a21cfb-4aff-42cb-aeaa-5b3fc99564d3)
 
 5.<br>
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/86c34f9a-e869-4ab2-9768-19a81f36ce62)
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/71d8695b-59e0-47d0-93d2-3fdbb7ca27d9)
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/076bd863-e29d-420c-b254-5918927f1de6)

 6.<br> 
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/803e0c28-025b-4893-b2b4-128f37371684)
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/4bbefe83-18b4-4ac4-85fa-28bb1280a49a)
 ![image](https://github.com/0xAlbertLin/orderItemProject_20240206/assets/46127917/cb6a6eba-52b8-4e03-9c63-df30d5fc6d1e)

Q2: Explain the potential problems in the following program and how to resolve them.
 ```
 public class Sample {
  public static void main(String[] args) {
   List<Shape> list = new ArrayList<>();
   list.add(new Rectangle());
   list.add(new Circle());
   for (Shape s: list) {
    new GraphicEditor().drawShape(s);
   }
  }
 }

 class Shape {
  int type;
 }

 class Rectangle extends Shape {
  Rectangle() {
   super.type = 1;
  }
 }
 
 class Circle extends Shape {
  Circle() {
   super.type = 2;
  }
 }
 
 class GraphicEditor {
  public void drawShape(Shape s) {
   if (s.type == 1)
    drawRectangle(s);
   else if (s.type == 2)
    drawCircle(s);
  }
  public void drawCircle(Circle r) {
   // drawCircle
  }
  public void drawRectangle(Rectangle r) {
   // drawRectangle
  }
 }
```
Ans: <br>
＊Compile Time Problem： <br>
 1.因為 GraphicEditor.drawShape() 基於多型，故參數為Shape；需修改程式，才會使編譯成功。
 ```
    if (s.type == 1)
      drawRectangle((Rectangle)s);
    else if (s.type == 2)
      drawCircle((Circle)s);
 ```
＊Design Issue： <br>
 1.將 Shape.type 修改為 final 避免 type 被修改且一定要賦予資料，如下：
 ```
  class Shape {
    final int type;
   Shape(int type){
    this.type = type;
   }
  }
  class Rectangle extends Shape {
    Rectangle() {
      super(1);
    }
  }
  class Circle extends Shape {
    Circle() {
      super(2);
    }
  }
 ```
 2.若Rectangle、Circle 如範例僅作為「常數」，可再進一步修改如下： 
 ```
 public class Sample {
  public static void main(String[] args) {
   List<Shape> list = new ArrayList<>();
   list.add(Shape.Rectangle);
   list.add(Shape.Circle);
   for (Shape s: list) {
    new GraphicEditor().drawShape(s);
   }
  }
 }

 enum Shape {
  Rectangle(1),Circle(2);
  int type;
  Shape(int type){
   this.type = type;
  }
 }

 class GraphicEditor {
  public void drawShape(Shape s) {
    switch (s) {
     case Rectangle:
      drawRectangle(s);
      break;
     case Circle:
      drawCircle(s);
      break;    
    }
  }
  public void drawCircle(Shape r) {
   // drawCircle
  }
  public void drawRectangle(Shape r) {
   // drawRectangle
  }
 }
```

