package dto.to;

import java.util.ArrayList;
import java.util.List;

public class AvatarTOs {

    private List<AvatarTO> avatars = new ArrayList<>();

    public AvatarTOs()
    {
        super();
    }

    public List<AvatarTO> getAvatars()
    {
        return avatars;
    }

    public void setAvatars(List<AvatarTO> avatars)
    {
        this.avatars = avatars;
    }

    public void addAvatarTO(AvatarTO avatarTO)
    {
        avatars.add(avatarTO);
    }

}
