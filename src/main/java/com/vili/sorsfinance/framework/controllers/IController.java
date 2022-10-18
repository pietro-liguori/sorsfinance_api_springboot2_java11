package com.vili.sorsfinance.framework.controllers;

import com.vili.sorsfinance.framework.IDataTransferObject;

public interface IController<T extends IDataTransferObject> extends SearchController, PersistenceController<T> {

}