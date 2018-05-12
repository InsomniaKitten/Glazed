@file:JvmName("Worlds")

package net.insomniakitten.glazed.extensions

/*
 *  Copyright 2018 InsomniaKitten
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

typealias BlockAccess = IBlockAccess

operator fun BlockAccess.get(pos: BlockPos) = AccessPosition(this, pos)

operator fun World.get(pos: BlockPos) = WorldPosition(this, pos)

open class AccessPosition internal constructor(
        protected val access: BlockAccess,
        protected val pos: BlockPos
) {
    open val state: IBlockState
        get() = access.getBlockState(pos)

    open val entity: TileEntity?
        get() = access.getTileEntity(pos)

    fun doesSideBlockRendering(side: EnumFacing): Boolean {
        return state.doesSideBlockRendering(access, pos, side)
    }

    val isReplaceable get() = state.block.isReplaceable(access, pos)
}

class WorldPosition internal constructor(
        world: World,
        pos: BlockPos
) : AccessPosition(world, pos) {
    private val world = access as World

    override var state: IBlockState
        get() = super.state
        set(value) {
            world.setBlockState(pos, value)
        }

    override var entity: TileEntity?
        get() = super.entity
        set(value) = if (value != null) {
            world.setTileEntity(pos, value)
        } else world.removeTileEntity(pos)
}
