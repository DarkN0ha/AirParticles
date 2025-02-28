package showAll

import ParticlesItem
import csstype.Cursor
import csstype.Display
import csstype.em
import csstype.px
import emotion.react.css
import hex
import mui.icons.material.MapSharp
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div

external interface ShowAllProps : Props {
    var edit: Boolean
    var particlesList: List<ParticlesItem>
    var selectedItem: ParticlesItem?
    var onDelete: (ParticlesItem) -> Unit
    var onSelectedItem: (ParticlesItem) -> Unit
}

var showAll = FC<ShowAllProps> { props ->
    for (particle in props.particlesList) {
        div {
            css {
                height = 4.em
                display = Display.block
                paddingTop = 8.px
                paddingRight = 8.px
                paddingBottom = 8.px
                paddingLeft = 32.px
                backgroundColor = hex("#E7E9EB")
                hover {
                    backgroundColor = hex("#CCCCCC")
                }
                if (particle == props.selectedItem) {
                    backgroundColor = hex("#04AA6D")
                    hover {
                        backgroundColor = hex("#04AA6D")
                    }
                }
            }
            a {
                css {
                    display = Display.block
                }
                +"Sensor: ${particle.location}"
                div {
                    css {
                        width = 20.px
                        cursor = Cursor.pointer
                    }
                    MapSharp()
                    onClick = {
                        var loc = "https://www.google.com/maps/search/?api=1&query=" + particle.location.trim()
                        js("window.open(loc, '_blank', 'noopener,noreferrer')")
                    }
                }
            }
            a {
                css {
                    display = Display.block
                }
                +"Reading: ${particle.particles}"
            }
            
            onClick = {
                props.onSelectedItem(particle)
            }
        }
        if (particle == props.selectedItem) {
            showMore {
                editMore = props.edit
                item = particle
                onDelete = { change ->
                    props.onDelete(change)
                }
            }
        }
    }
}