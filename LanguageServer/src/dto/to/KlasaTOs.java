package dto.to;

import java.util.ArrayList;
import java.util.List;

public class KlasaTOs {

    private List<KlasaTO> klases = new ArrayList<>();

    public KlasaTOs()
    {
        super();
    }

    public List<KlasaTO> getKlases()
    {
        return klases;
    }

    public void setKlases(List<KlasaTO> klases)
    {
        this.klases = klases;
    }

    public void addKlasaTO(KlasaTO klasaTO)
    {
        klases.add(klasaTO);
    }

}
