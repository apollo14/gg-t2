package gg.t1

import model.gpw.Register

class RegisterResourceController {
    static responseFormats = ['json']

    def wallet

    def list()  {
        render wallet.registers.values()
    }

    def getRegister = {
        Register register =  wallet.registers.get(params.get("id"))
        render register
    }

    def getOperations = {
        Register register =  wallet.registers.get(params.get("id"))
        render register.operations
    }

    def getCalculations = {
        Register register =  wallet.registers.get(params.get("id"))
        render register.calculations
    }
}
