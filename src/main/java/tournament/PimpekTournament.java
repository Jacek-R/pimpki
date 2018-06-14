package tournament;

import supplier.Supplier;
import match.Match;
import configuration.Configuration;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class PimpekTournament implements Tournament {

    private final Supplier supplier;
    private final Map<Pimpek, PimpekStatistics> results;

    public static Tournament getInstance(Supplier supplier) {
        return new PimpekTournament(supplier);
    }

    private PimpekTournament(Supplier supplier) {
        this.supplier = supplier;
        this.results = new HashMap<>();
    }


    @Override
    public void begin() throws FileNotFoundException {

        Match match;
        Configuration configuration = supplier.getConfiguration();



        int matchQuantity = configuration.getMatchQuantity();
        Map<Pimpek, PimpekStatistics> singleMatchResult;

        for (int i=0; i<matchQuantity; i++) {

            match = supplier.getNewMatch();

            // execute match, build result
            singleMatchResult = match.executeMatch();


            System.out.println(singleMatchResult);

            // todo build result
//            System.out.println(results);
        }
    }
}
