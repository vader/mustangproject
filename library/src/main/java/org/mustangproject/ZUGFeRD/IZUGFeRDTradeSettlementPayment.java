/** **********************************************************************
 *
 * Copyright 2019 by ak on 12.04.19.
 *
 * Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *********************************************************************** */
package org.mustangproject.ZUGFeRD;

import java.text.SimpleDateFormat;

import org.mustangproject.XMLTools;

public interface IZUGFeRDTradeSettlementPayment extends IZUGFeRDTradeSettlement {

	/**
	 * get payment information text. e.g. Bank transfer
	 *
	 * @return payment information text
	 */
	default String getOwnPaymentInfoText() {
		return null;
	}

	/**
	 * BIC of the sender
	 *
	 * @return the BIC code of the recipient sender's bank
	 */
	default String getOwnBIC() {
		return null;
	}


	/**
	 * BLZ of the sender
	 *
	 * @return the BLZ code of the recipient sender's bank
	 */
	default String getOwnBLZ() {
		return null;
	}


	/**
	 * Bank name of the sender
	 *
	 * @return the name of the sender's bank
	 */
	default String getOwnBankName() {
		return null;
	}


	/**
	 * IBAN of the sender
	 *
	 * @return the IBAN of the invoice sender's bank account
	 */
	default String getOwnIBAN() {
		return null;
	}


	/**
	 * IBAN of the sender
	 *
	 * @return the Account Number of the invoice sender's bank account
	 */
	default String getOwnKto() {
		return null;
	}
	
	default String getSettlementXML() {
		String xml = "			<ram:SpecifiedTradeSettlementPaymentMeans>\n"
				+ "				<ram:TypeCode>42</ram:TypeCode>\n"
				+ "				<ram:Information>Überweisung</ram:Information>\n"
				+ "				<ram:PayeePartyCreditorFinancialAccount>\n"
				+ "					<ram:IBANID>" + XMLTools.encodeXML(getOwnIBAN()) + "</ram:IBANID>\n"; //$NON-NLS-2$
			if (getOwnKto()!=null) {
				xml+= "					<ram:ProprietaryID>" + XMLTools.encodeXML(getOwnKto()) + "</ram:ProprietaryID>\n"; //$NON-NLS-2$
				
			}
				xml+= "				</ram:PayeePartyCreditorFinancialAccount>\n"
				+ "				<ram:PayeeSpecifiedCreditorFinancialInstitution>\n"
				+ "					<ram:BICID>" + XMLTools.encodeXML(getOwnBIC()) + "</ram:BICID>\n" //$NON-NLS-2$
				// + " <ram:Name>"+trans.getOwnBankName()+"</ram:Name>\n"
				// //$NON-NLS-2$
				+ "				</ram:PayeeSpecifiedCreditorFinancialInstitution>\n"
				+ "			</ram:SpecifiedTradeSettlementPaymentMeans>\n";
		return xml;
	}
	
	
	/* I'd love to implement getPaymentXML() and put <ram:DueDateDateTime> there because this is where it belongs
	 * unfortunately, the due date is part of the transaction which is not accessible here :-(
	 */


}
