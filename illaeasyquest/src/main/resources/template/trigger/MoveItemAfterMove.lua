--[[
  This file is part of the Illarion project.

  Copyright © 2014 - Illarion e.V.

  Illarion is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  Illarion is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  ]]
require("questsystem.base")
module("MoveItemAfterMove", package.seeall)

-- category: item
-- An item was moved -- Ein Gegenstand wurde bereits bewegt

local QUEST_NUMBER = 0
local PRECONDITION_QUESTSTATE = 0
local POSTCONDITION_QUESTSTATE = 0

local POSITION = POSITION -- Map position -- Position auf der Karte
local RADIUS = INTEGER -- Radius -- Radius


function MoveItemAfterMove(PLAYER, itemBefore, item)
    if PLAYER:isInRangeToPosition(POSITION, RADIUS)
            and ADDITIONALCONDITIONS(PLAYER)
            and questsystem.base.fulfilsPrecondition(PLAYER, QUEST_NUMBER, PRECONDITION_QUESTSTATE) then
        PLAYER:inform(TEXT_DE, TEXT_EN)

        HANDLER(PLAYER)

        questsystem.base.setPostcondition(PLAYER, QUEST_NUMBER, POSTCONDITION_QUESTSTATE)
        return true
    end

    return false
end


-- local TEXT_DE = TEXT -- German Text after movement -- Deutscher Text nach Bewegung
-- local TEXT_EN = TEXT -- English Text after movement -- Englischer Text nach Bewegung