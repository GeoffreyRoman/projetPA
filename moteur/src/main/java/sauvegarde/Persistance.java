package sauvegarde;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import moteur.Partie;
import moteur.Robot;

public class Persistance {
	
    public static void save(ArrayList<Robot> robots, Class attaque, Class graphisme, 
    		Class deplacement, Class barreDeVie, Class nomRobot, String fileName) {
    		ObjectOutputStream oos;
		try 
		{
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			try 
			{
				oos.writeObject(robots);
				oos.writeObject(attaque);
				oos.writeObject(graphisme);
				oos.writeObject(deplacement);
				oos.writeObject(barreDeVie);
				oos.writeObject(nomRobot);
				oos.flush();
	        } 
			finally 
			{
				oos.close();
	        }
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
    
    public static Partie charger(String fileName) 
    {
    		ArrayList<Robot> robots;
    		Class attaque;
    		Class graphisme;
    		Class deplacement;
    		Class barreDeVie;
    		Class nomRobot;
    		Partie partie = null;
    		
    		ObjectInputStream ois;
		try 
		{
			ois = new ObjectInputStream(new FileInputStream(fileName));
			try 
	        {
	            robots = (ArrayList<Robot>)ois.readObject();
	            attaque = (Class)ois.readObject();
		    		graphisme = (Class)ois.readObject();
		    		deplacement = (Class)ois.readObject();
		    		barreDeVie = (Class)ois.readObject();
		    		nomRobot = (Class)ois.readObject();
		    		partie = new Partie(robots, attaque, graphisme, deplacement, barreDeVie, nomRobot);
	        } 
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        finally 
	        {
	            ois.close();
	        }
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        return partie;
    }
}
