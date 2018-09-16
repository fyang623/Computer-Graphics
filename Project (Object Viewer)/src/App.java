// Project 1 initial source code
// CMSC427 fall 2017

// Class App sets up MVC architecture
// ModelViewController
// 
// More properly MV-C, ModelView-Controller
// with two objects 

public class App {

	static Code myModelView;
	static Controller myController;
	
	public static void main(String[] args) {
		myController = new Controller(); 
		myModelView = new Code(myController); 
		myController.addCode(myModelView);
	}
}
