package com.vexoid.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.JSONObject;
import com.badlogic.gdx.Input;

public class Options {
	// contstants for arrow keys
	public static final int upArrow = com.badlogic.gdx.Input.Keys.UP;
	public static final int leftArrow = com.badlogic.gdx.Input.Keys.LEFT;
	public static final int downArrow = com.badlogic.gdx.Input.Keys.DOWN;
	public static final int rightArrow = com.badlogic.gdx.Input.Keys.RIGHT;
	// url to int codes
	// https://libgdx.badlogicgames.com/nightlies/docs/api/constant-values.html#com.badlogic.gdx.Input.Keys.SYM
	private static String defaultControlsString = "{\"up\": \"51\",\"down\":\"47\", \"left\":\"29\", \"right\":\"32\", \"fire\":\"62\","
			+ " \"changeSpread\":\"30\",\"changeMode\":\"34\", \"slow\":\"59\"}";
	public static JSONObject controls;

	public Options() {
		// generate default controls
		try {
			controls = new JSONObject(defaultControlsString);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} //read controls from file
		readControls(validateFile("C:\\\\Vexoids\\controls.txt"));
	}

	// this method ensures that a file exists and creates it if needed
	// exists and populates it with the controls if necessary
	public static File validateFile(String path) {
		File passedFile = new File(path);
		// try block for File writing
		try {
			// invoked if the file/directory does NOT exist
			if (!passedFile.exists()) {
				// invoked if the directory does not exist
				if (!passedFile.getParentFile().exists()) {
					if (passedFile.getParentFile().mkdir()) {
						passedFile.createNewFile();
						System.out.println("File and directory created at " + path.toString());
						generateDefaults(passedFile);
					} else {
						throw new IOException("Failed to create directory " + passedFile.getParent());
					}
				} // invoked if just the file does not exist
				else {
					passedFile.createNewFile();
					System.out.println("File created at " + path.toString());
					generateDefaults(passedFile);
				}
			} // invoked if the file DOES exist
			else {
				System.out.println("controls file exists at " + path.toString());
			}
		} catch (java.io.IOException e) {
			System.err.print(e);
			System.exit(1);
		}
		return passedFile;
	}

	// this method grabs the contents from the controls file and creates a new JSON object
	public static void readControls(File passedFile) {
		try (Scanner scanner = new Scanner(passedFile);) {
			String newControlString = scanner.nextLine();
			scanner.close();
			controls = new JSONObject(newControlString);// convert new control schema to a JSON Object
		} catch (Exception e) {// catch all.. deletes the file then recreates with default controls
			if (passedFile.delete()){
				System.err.println("reverting controls file due to an error in Options readControls");
				readControls(validateFile("C:\\\\Vexoids\\controls.txt"));
			} else {
				System.exit(1);
			}
		}
	}

	// this method writes to the controls file. Takes the index and the int code of the remapped key
	//0=up 1=down 2=left 3=right 4=fire 5=changeSpread 6=changeMode 7=slow
	public static boolean writeControls(int index, int newControl){
		File ctrlFile = validateFile("C:\\\\Vexoids\\controls.txt");
		String temp = "error";
		try {
			PrintWriter w = new PrintWriter(ctrlFile);
			StringBuilder controlsString = new StringBuilder("{");
			for(int i = 0;i<8;i++){
				if(i==index){
					switch(i){
				    case 0: controlsString.append("\"up\": \""+newControl+"\"");temp="up";break;
				    case 1: controlsString.append("\"down\": \""+newControl+"\"");temp="down";break;
				    case 2: controlsString.append("\"left\": \""+newControl+"\"");temp="left";break;
				    case 3: controlsString.append("\"right\": \""+newControl+"\"");temp="right";break;
				    case 4: controlsString.append("\"fire\": \""+newControl+"\"");temp="fire";break;
				    case 5: controlsString.append("\"changeSpread\": \""+newControl+"\"");temp="changeSpread";break;
				    case 6: controlsString.append("\"changeMode\": \""+newControl+"\"");temp="changeMode";break;
				    case 7: controlsString.append("\"slow\": \""+newControl+"\"");temp="slow";break;
				    }
					System.out.println("rebound "+ temp +" to " + Input.Keys.toString(newControl));
				}
				else{
					switch(i){
				    case 0: controlsString.append("\"up\": \""+controls.getInt("up")+"\"");break;
				    case 1: controlsString.append("\"down\": \""+controls.getInt("down")+"\"");break;
				    case 2: controlsString.append("\"left\": \""+controls.getInt("left")+"\"");break;
				    case 3: controlsString.append("\"right\": \""+controls.getInt("right")+"\"");break;
				    case 4: controlsString.append("\"fire\": \""+controls.getInt("fire")+"\"");break;
				    case 5: controlsString.append("\"changeSpread\": \""+controls.getInt("changeSpread")+"\"");break;
				    case 6: controlsString.append("\"changeMode\": \""+controls.getInt("changeMode")+"\"");break;
				    case 7: controlsString.append("\"slow\": \""+controls.getInt("slow")+"\"");break;
				    }
				}
				if(i<7)
					controlsString.append(",");
			}
			controlsString.append("}");
			w.print(controlsString.toString());
			w.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// this method takes a file object and populates that file with the controls specified
	public static void generateDefaults(File f) {
		// try block in case something goes wrong with the file
		try {
			PrintWriter w = new PrintWriter(f);
			w.print(defaultControlsString);
			w.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		readControls(validateFile("C:\\\\Vexoids\\controls.txt"));
	}
}