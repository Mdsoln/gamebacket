package com.gamebacket.vercel.app.service.inter;

import com.gamebacket.vercel.app.dto.AccessoryRequest;
import com.gamebacket.vercel.app.dto.GamePublisher;

public interface AdminInterface {
    void publishNewGame(GamePublisher gamePublisher);

    void publishAccessories(AccessoryRequest accessoryRequest);

}
