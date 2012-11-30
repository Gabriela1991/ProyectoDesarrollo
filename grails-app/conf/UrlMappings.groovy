class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
                
		"/"(controller: "Persona", action: "inicio")
		"500"(view:'/error')
	}
}
