package io.arichter.delivery.contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "get selling places"

    request {
        url("/selling_places/latitude/1/longitude/2")
        method GET()
    }

    response {
        status OK()
        body '''{
          "id": "25",
          "document": "20.053.623/0001-30",
          "tradingName": "Bar Legal",
          "ownerName": "Daniel Henrique",
          "coverageArea": null,
          "address": null
        }'''
        headers {
            contentType applicationJson()
        }
    }
}
