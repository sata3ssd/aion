/*
 * Copyright (c) 2017-2018 Aion foundation.
 *
 *     This file is part of the aion network project.
 *
 *     The aion network project is free software: you can redistribute it
 *     and/or modify it under the terms of the GNU General Public License
 *     as published by the Free Software Foundation, either version 3 of
 *     the License, or any later version.
 *
 *     The aion network project is distributed in the hope that it will
 *     be useful, but WITHOUT ANY WARRANTY; without even the implied
 *     warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *     See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with the aion network project source files.
 *     If not, see <https://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Aion foundation.
 */

package org.aion.api.server.types;

import static junit.framework.TestCase.assertEquals;

import java.math.BigInteger;
import org.aion.base.type.Address;
import org.json.JSONObject;
import org.junit.Test;

public class ArgTxCallTest {

    @Test
    public void testFromJsonContractCreateDefaults() {
        long nrgLimit = 250_000L;
        long nrgPrice = 10L;

        JSONObject tx = new JSONObject();
        ArgTxCall txCall = ArgTxCall.fromJSON(tx, nrgPrice, 0, nrgLimit);

        assertEquals(Address.EMPTY_ADDRESS(), txCall.getFrom());
        assertEquals(Address.EMPTY_ADDRESS(), txCall.getTo());
        assertEquals(0, txCall.getData().length);
        assertEquals(BigInteger.ZERO, txCall.getNonce());
        assertEquals(BigInteger.ZERO, txCall.getValue());
        assertEquals(nrgLimit, txCall.getNrg());
        assertEquals(nrgPrice, txCall.getNrgPrice());
    }

    @Test
    public void testFromJsonTxDefaults() {
        long nrgLimit = 80_000L;
        long nrgPrice = 10L;

        String toAddr = "0xa076407088416d71467529d8312c24d7596f5d7db75a5c4129d2763df112b8a1";

        JSONObject tx = new JSONObject();

        tx.put("to", toAddr);
        ArgTxCall txCall = ArgTxCall.fromJSON(tx, nrgPrice, nrgLimit, 0);

        assertEquals(Address.EMPTY_ADDRESS(), txCall.getFrom());
        assertEquals(new Address(toAddr), txCall.getTo());
        assertEquals(0, txCall.getData().length);
        assertEquals(BigInteger.ZERO, txCall.getNonce());
        assertEquals(BigInteger.ZERO, txCall.getValue());
        assertEquals(nrgLimit, txCall.getNrg());
        assertEquals(nrgPrice, txCall.getNrgPrice());
    }
}
