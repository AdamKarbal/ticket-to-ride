package fr.cotedazur.univ.polytech.teamO;

import com.beust.jcommander.Parameter;

public class Commands {

    @Parameter(names = "--2thousands", description = "Execution de 2 x 1000 parties")
    public boolean TwoThousandCommand;

    @Parameter(names = "--demo", description = "Mode démo avec logs")
    public boolean DemoCommand;

    @Parameter(names ="--csv", description ="Ajout de stats au CSV")
    public boolean CsvCommand;

    public boolean isTwoThousands(){
        return TwoThousandCommand;
    }

    public boolean isDemoCommand(){
        return DemoCommand;
    }

    public boolean isCsvCommand(){
        return CsvCommand;
    }
}

