package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.model.Utensil;

public interface UtensilDataAccess {
    public void addUtensil(Utensil utensil) throws AddErrorException;
    public void deleteUtensil(Utensil utensil) throws DeleteErrorException;}
